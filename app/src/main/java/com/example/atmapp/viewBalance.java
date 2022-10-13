package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class viewBalance extends AppCompatActivity {
    TextView balance;
    Button avaiViewbtn;
    DBhelper dBhelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_balance);
        balance = findViewById(R.id.viewbalance4);
        avaiViewbtn =findViewById(R.id.view_xml_backButton);
        click();
        dataBase();


    }

    void click(){
        avaiViewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewBalance.this,Activity3.class);
                startActivity(intent);
            }
        });
    }

    void dataBase(){
        dBhelper=new DBhelper(this);
        SQLiteDatabase db =dBhelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from accountDetails where accountNumber=?",new String[]{"161601"});
        while (cursor.moveToNext()){
            System.out.println("qwertyuiop"+cursor.getString(4));
        }

    }
}