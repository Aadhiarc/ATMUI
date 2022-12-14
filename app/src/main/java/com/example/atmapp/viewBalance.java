package com.example.atmapp;

import static com.example.atmapp.DBhelper.ACCOUNT_DETAILS;
import static com.example.atmapp.DBhelper.COLUMN_ACCOUNT_NUMBER;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class viewBalance extends AppCompatActivity {
    TextView balance;
    Button avaiViewbtn;
    DBhelper dBhelper;
    List<String> arrayList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_balance);
        balance = findViewById(R.id.viewBalance);
        avaiViewbtn = findViewById(R.id.view_xml_backButton);
        viewBalance();
        click();


    }

    void click() {
        avaiViewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewBalance.this, Activity3.class);
                startActivity(intent);
            }
        });
    }

    void viewBalance(){
        SharedPreferences sp=getApplicationContext().getSharedPreferences("localdb",MODE_PRIVATE);
        String acnum=sp.getString("loginAccountNumber","");
        dBhelper=new DBhelper(viewBalance.this);
        SQLiteDatabase sqLiteDatabase =dBhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from "+ACCOUNT_DETAILS+" where "+COLUMN_ACCOUNT_NUMBER+"=?",new String[]{acnum});
        while (cursor.moveToNext()){
           String view_balance= cursor.getString(3);
            balance.setText(view_balance);
        }
    }
}