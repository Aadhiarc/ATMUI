package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class depositAmount extends AppCompatActivity {
    public TextInputEditText depositAmount;
    TextView bal;
    public Button depositButton,depositBack;
    DBhelper dbobj;
    List<String> arrayList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_deposit_amount);
        depositAmount = findViewById(R.id.deposit_amount_edittext);
        depositButton = findViewById(R.id.deposit_xml_button);
        bal= findViewById(R.id.depositViewBalance);
        depositBack=findViewById(R.id.deposit_xml_backButton);
        dbobj = new DBhelper(this);
        click();
    }

    void click() {
         depositButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SQLiteDatabase d =dbobj.getReadableDatabase();
                 Cursor cursord= d.rawQuery("select * from accountDetails",null,null);
                 arrayList=new ArrayList<String>();
                 while(cursord.moveToNext()){
                     arrayList.add(cursord.getString(1));
                 }
                 SharedPreferences mysharedPreferences=getSharedPreferences("Aadhi",MODE_PRIVATE);
                 String acNum=mysharedPreferences.getString("accountNumber","empty");
                 for(int i=0;i<arrayList.size();i++){
                     if(arrayList.get(i).equals(acNum)){
                         SQLiteDatabase db1 = dbobj.getWritableDatabase();
                         ContentValues contentValues = new ContentValues();
                         String deposit_amount =depositAmount.getText().toString();
                         contentValues.put("depositAmount",deposit_amount);
                         db1.update("accountDetails",contentValues,"accountNumber=?",new String[]{acNum});
                         SQLiteDatabase depositRead=dbobj.getReadableDatabase();
                         Cursor read=depositRead.rawQuery("select * from accountDetails",null,null);
                         while (read.moveToNext()){
                             bal.setText(  read.getString(4));
                         }
                     }
                 }
             }
         });

         depositBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent4 = new Intent(com.example.atmapp.depositAmount.this,Activity3.class);
                 startActivity(intent4);
             }
         });
    }

}