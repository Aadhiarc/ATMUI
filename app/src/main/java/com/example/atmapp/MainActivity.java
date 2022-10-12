package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText username,accountNumber,pin,confirmPin;
    Button signup,reg_login;
    DBhelper dbobject;
    boolean check;
    public  long dbSize;
    public List<String> arraylist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        accountNumber=findViewById(R.id.accountNumber);
        pin=findViewById(R.id.accountPin);
        confirmPin=findViewById(R.id.confirm_accountPin);
        signup=findViewById(R.id.button1);
        reg_login=findViewById(R.id.reg_loginbtn);
        dbobject= new DBhelper(this);
        click();

    }
    public void click(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbobject.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String user_name= username.getText().toString();
                String account_number=accountNumber.getText().toString();
                String create_pin =pin.getText().toString();
                String confirm_pin =confirmPin.getText().toString();
                if(TextUtils.isEmpty(user_name)&&TextUtils.isEmpty(account_number)&&TextUtils.isEmpty(create_pin)&&TextUtils.isEmpty(confirm_pin)){
                    check =false;
                    username.setError("username should not be empty");
                    accountNumber.setError("account number should not be empty");
                    pin.setError("pin should not be empty");
                    confirmPin.setError("confirm pin should not be empty");
                }
                if(create_pin.equals(confirm_pin)){
                    check=true;
                }else{
                    confirmPin.setError("create pin and confirm pin should be same");
                }
                if(check==true) {
                    contentValues.put("username", user_name);
                    contentValues.put("accountNumber", account_number);
                    contentValues.put("accountPin", create_pin);
                    contentValues.put("confirmPin", confirm_pin);
                     dbSize = db.insert("accountDetails", null, contentValues);
                    Intent intent = new Intent(MainActivity.this,loginActivity.class);
                    startActivity(intent);
                }
                SQLiteDatabase db1=dbobject.getReadableDatabase();
                Cursor cursor=db1.rawQuery("Select * from accountDetails",null,null);
                arraylist=new ArrayList<String>();
                while (cursor.moveToNext()){
                    arraylist.add(cursor.getString(0));
                    arraylist.add(cursor.getString(1));
                    arraylist.add(cursor.getString(2));
                }
                System.out.println(arraylist);
            }
        });
        reg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }

}