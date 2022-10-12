package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {

    EditText laccountNumber,laccountPin;
    Button login,back;
    DBhelper dbobject;
    String accountNumberDb;
    String accountPinDb;
    boolean check;
    boolean check1;
    List<String> arrayList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbobject= new DBhelper(this);
        laccountNumber=findViewById(R.id.login_accountNumber);
        laccountPin=findViewById(R.id.login_accountPin);
        login=findViewById(R.id.loginbtn);
        back=findViewById(R.id.back_reg);
        click();
    }

    void click(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =dbobject.getReadableDatabase();
                Cursor cursor=db.rawQuery("select * from accountDetails",null,null);
                arrayList=new ArrayList<String>();
                if(TextUtils.isEmpty(laccountNumber.getText().toString())&&TextUtils.isEmpty(laccountPin.getText().toString())){
                    check=true;
                }
                if(check==true){
                    laccountNumber.setError("account number is empty");
                    laccountPin.setError("account pin is empty");
                }
                while (cursor.moveToNext()){
                    arrayList.add(accountNumberDb= cursor.getString(1));
                    arrayList.add(accountPinDb=cursor.getString(2));
                }

                for(int i=0;i<arrayList.size();i++){
                    if(laccountNumber.getText().toString().equals(arrayList.get(i))&&laccountPin.getText().toString().equals(arrayList.get(i+1))){
                        check1=true;
                        i=i+1;
                        Intent intent = new Intent(loginActivity.this,Activity3.class);
                        startActivity(intent);
                    }
                }
                if(check1==true){
                    Toast.makeText(loginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(loginActivity.this, "user not found", Toast.LENGTH_SHORT).show();
                }

            }

        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }


}