package com.example.atmapp;

import static com.example.atmapp.DBhelper.ACCOUNT_DETAILS;
import static com.example.atmapp.DBhelper.COLUMN_ACCOUNT_NUMBER;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class depositAmount extends AppCompatActivity {
    public TextInputEditText depositAmount;
    TextView balance;
    public Button depositButton,depositBack;
    DBhelper dBhelper;
    String deposit_amount;
    AlertDialog.Builder builder;
    String intialBal;
    String view_balance;

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
        balance= findViewById(R.id.deposit_ViewBalance);
        depositBack=findViewById(R.id.deposit_xml_backButton);
        dBhelper = new DBhelper(this);
        viewBalance();
        click();
    }

    void click() {
         depositButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 try{
                     SharedPreferences sp =getApplicationContext().getSharedPreferences("localdb",MODE_PRIVATE);
                     String acnum=sp.getString("loginAccountNumber","");
                     SQLiteDatabase sqLiteDatabase1=dBhelper.getReadableDatabase();
                     Cursor cursor=sqLiteDatabase1.rawQuery("select * from "+ACCOUNT_DETAILS+" where "+COLUMN_ACCOUNT_NUMBER+"=?",new String[]{acnum});
                     while (cursor.moveToNext()){
                         intialBal=cursor.getString(3);
                     }
                     int intial_bal=Integer.parseInt(intialBal);
                     SQLiteDatabase sqLiteDatabase =dBhelper.getWritableDatabase();
                     String deposit_Amount=depositAmount.getText().toString();
                     int intial_deposit_amount=Integer.parseInt(deposit_Amount);
                     int updateValue=intial_deposit_amount+intial_bal;
                     deposit_amount= String.valueOf(updateValue);
                     ContentValues contentValues = new ContentValues();
                     contentValues.put("depositAmount",deposit_amount);
                     long val = sqLiteDatabase.update(ACCOUNT_DETAILS,contentValues,COLUMN_ACCOUNT_NUMBER +" =? ",new String[]{acnum});
                     balance.setText(deposit_amount);
                     alert();
                 }catch (Exception e){

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

       void alert(){
          builder = new AlertDialog.Builder(this);
           builder.setTitle("Deposit confirmation").setMessage("Do you want to deposit amount ?").setCancelable(false)
                   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           Toast.makeText(depositAmount.this, "Amount Deposited successfully", Toast.LENGTH_SHORT).show();
                       }
                   }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           dialogInterface.cancel();
                       }
                   }).show();
       }
       void viewBalance(){
               SharedPreferences sp=getApplicationContext().getSharedPreferences("localdb",MODE_PRIVATE);
               String acnum=sp.getString("loginAccountNumber","");
               dBhelper=new DBhelper(depositAmount.this);
               SQLiteDatabase sqLiteDatabase =dBhelper.getReadableDatabase();
               Cursor cursor=sqLiteDatabase.rawQuery("select * from "+ACCOUNT_DETAILS+" where "+COLUMN_ACCOUNT_NUMBER+"=?",new String[]{acnum});
               while (cursor.moveToNext()){
                    view_balance= cursor.getString(3);
                    balance.setText(view_balance);
               }

       }
}