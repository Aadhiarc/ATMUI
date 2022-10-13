package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    List<String> arratList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
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
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =dbobject.getReadableDatabase();
                Cursor cursor=db.rawQuery("select * from accountDetails where accountNumber and accountPin",null,null);
                if(TextUtils.isEmpty(laccountNumber.getText().toString())&&TextUtils.isEmpty(laccountPin.getText().toString())){
                    check=true;
                }
                if(check==true){
                    laccountNumber.setError("account number is empty");
                    laccountPin.setError("account pin is empty");
                }
                arratList = new ArrayList<String>();
                while (cursor.moveToNext()){
                          arratList.add(cursor.getString(cursor.getColumnIndex("accountNumber")));
                          arratList.add(cursor.getString(cursor.getColumnIndex("accountPin")));
                }
                for(int i=0;i<arratList.size();i++) {
                    if (laccountNumber.getText().toString().equals(arratList.get(i)) && laccountPin.getText().toString().equals(arratList.get(i+1))) {
                        String acNum=String.valueOf(laccountNumber.getText());
                        SharedPreferences mysharedPreferences=getSharedPreferences("Aadhi",MODE_PRIVATE);
                        SharedPreferences.Editor editor= mysharedPreferences.edit();
                        editor.putString("accountNumber",acNum);
                        editor.commit();
                        i=i+1;
                        Toast.makeText(loginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(loginActivity.this,Activity3.class);
                        startActivity(intent);
                    }
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