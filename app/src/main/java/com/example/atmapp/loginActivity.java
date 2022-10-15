package com.example.atmapp;

import static com.example.atmapp.DBhelper.ACCOUNT_DETAILS;
import static com.example.atmapp.DBhelper.COLUMN_ACCOUNT_NUMBER;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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
    SharedPreferences sp;
    String name;
    String pin;
    List<String> arrayList;
    String loginAccountNumbers;
    AlertDialog.Builder builder;

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
        sp=getSharedPreferences("localdb",MODE_PRIVATE);
        click();
    }

    void click(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase=dbobject.getReadableDatabase();
                Cursor cursor=sqLiteDatabase.rawQuery("select * from accountDetails",null);
                arrayList = new ArrayList<>();
                while (cursor.moveToNext()){
                    arrayList.add(cursor.getString(1));
                }
                // i have created for loop to only iterate the account number in database
                      for(int i=0;i<arrayList.size();i++){
                          loginAccountNumbers=arrayList.get(i);
                          if(laccountNumber.getText().toString().equals(loginAccountNumbers)){
                               name=loginAccountNumbers;
                              SharedPreferences.Editor editor=sp.edit();
                              editor.putString("loginAccountNumber",name);
                              editor.commit();
                          }
                      }
                      getData();
                      if(laccountNumber.getText().toString().equals(name)&&laccountPin.getText().toString().equals(pin)){
                          Toast.makeText(loginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                          Intent intent = new Intent(loginActivity.this,Activity3.class);
                          startActivity(intent);
                      }else{
                          Toast.makeText(loginActivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        builder = new AlertDialog.Builder(this);
       builder.setTitle("Alert !!!")
               .setMessage("Do you want to logout")
               .setCancelable(false)
               .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                     finishAffinity();
                   }
               }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.cancel();
                   }
               });builder.show();

    }
    void getData(){
        SQLiteDatabase sqLiteDatabase = dbobject.getReadableDatabase();
         Cursor cursor= sqLiteDatabase.rawQuery("select * from "+ACCOUNT_DETAILS+" where " + COLUMN_ACCOUNT_NUMBER +" =? " ,new String[]{name});
        while (cursor.moveToNext()){
             pin =cursor.getString(2);
        }
    }
}