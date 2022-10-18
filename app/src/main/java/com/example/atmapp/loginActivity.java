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

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {

    EditText laccountNumber, laccountPin;
    TextInputLayout accountNumberLayout, accountPinLayout;
    Button login, back;
    DBhelper dbobject;
    SharedPreferences sp;
    String pin;
    AlertDialog.Builder builder;
    String userName;
    int count;
    boolean check=false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        dbobject = new DBhelper(this);
        accountNumberLayout = findViewById(R.id.login_accountNumberLayout);
        accountPinLayout = findViewById(R.id.login_accountPinLayout);
        laccountNumber = findViewById(R.id.login_accountNumber);
        laccountPin = findViewById(R.id.login_accountPin);
        login = findViewById(R.id.loginbtn);
        sp = getSharedPreferences("localdb", MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(laccountNumber.getText().toString())){
                    accountNumberLayout.setError("account Number should not be empty");
                }
                if(TextUtils.isEmpty(laccountPin.getText().toString())){
                    accountPinLayout.setError("account pin should not be empty");
                }

                dbobject=new DBhelper(loginActivity.this);
                SQLiteDatabase sqLiteDatabase1 =dbobject.getReadableDatabase();
                Cursor cursor1=sqLiteDatabase1.rawQuery("select * from "+ACCOUNT_DETAILS+" where "+COLUMN_ACCOUNT_NUMBER+"=?",new String[]{laccountNumber.getText().toString()});
                count = cursor1.getCount();
                if(!TextUtils.isEmpty(laccountNumber.getText().toString())){
                   check=true;
                }
                if(!TextUtils.isEmpty(laccountPin.getText().toString())){
                   check=true;
                }
                if(check){
                    if(count==0){
                        Toast.makeText(loginActivity.this, "user not found", Toast.LENGTH_SHORT).show();
                    }
                }
                while(cursor1.moveToNext()){
                     userName= cursor1.getString(1);
                    System.out.println(userName);
                    }
                if(laccountNumber.getText().toString().equals(userName)){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("loginAccountNumber", laccountNumber.getText().toString());
                    editor.commit();
                }
                getData();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert !!!")
                .setMessage("Do you want to exit the app")
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
                });
        builder.show();

    }

    void getData() {
        try {
            SQLiteDatabase sqLiteDatabase = dbobject.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + ACCOUNT_DETAILS + " where " + COLUMN_ACCOUNT_NUMBER + " =? ", new String[]{laccountNumber.getText().toString()});
            while (cursor.moveToNext()){
                pin = cursor.getString(2);
            }
            if (laccountNumber.getText().toString().equals(userName) && laccountPin.getText().toString().equals(pin)) {
                Toast.makeText(loginActivity.this, "login successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(loginActivity.this, Activity3.class);
                startActivity(intent);
            }
        }catch (Exception e){
            Toast.makeText(loginActivity.this, "invalid credentials", Toast.LENGTH_SHORT).show();
        }

    }
}