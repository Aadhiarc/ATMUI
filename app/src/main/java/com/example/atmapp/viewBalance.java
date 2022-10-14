package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_balance);
        balance=findViewById(R.id.viewBalance);
        avaiViewbtn =findViewById(R.id.view_xml_backButton);
        dataBase();
        click();


    }

    void click(){
        avaiViewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewBalance.this,Activity3.class);
                dataBase();
                startActivity(intent);
            }
        });
    }
    void dataBase(){
        dBhelper=new DBhelper(this);
        SQLiteDatabase db =dBhelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from accountDetails",null,null);
        arrayList=new ArrayList<String>();
        while (cursor.moveToNext()){
            arrayList.add(cursor.getString(1));
            arrayList.add(cursor.getString(4));
        }
        SharedPreferences mysharedPreferences=getSharedPreferences("Aadhi",MODE_PRIVATE);
        String acNum=mysharedPreferences.getString("accountNumber","empty");
        for(int i =0;i<arrayList.size();i++){
            if(arrayList.get(i).equals(acNum)){
                for(int j =0;j<arrayList.size();j++) {
                    String updateBal = arrayList.get(j);
                    balance.setText(updateBal);
                    String updatedBalance= getIntent().getStringExtra("updatedBalance");
                    boolean checking = Boolean.parseBoolean(getIntent().getStringExtra("checking"));
                    System.out.println(checking);
                    if(checking==true){
                        balance.setText(updatedBalance);
                    }

                }
            }
        }

    }
}