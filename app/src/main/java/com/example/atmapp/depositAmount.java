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

public class depositAmount extends AppCompatActivity {
    public TextInputEditText depositAmount;
    TextView bal;
    public Button depositButton,depositBack;
    DBhelper dbobj;

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
                 SQLiteDatabase db1 = dbobj.getWritableDatabase();
                 ContentValues contentValues = new ContentValues();
                 String deposit_amount =depositAmount.getText().toString();
                 contentValues.put("depositAmount",deposit_amount);
                 SharedPreferences mysharedPreferences=getSharedPreferences("Aadhi",MODE_PRIVATE);
                 String acNum=mysharedPreferences.getString("accountNumber","123456789");
                 db1.update("accountDetails",contentValues,"accountNumber=?",new String[]{acNum});
                 SQLiteDatabase db2=dbobj.getReadableDatabase();
                 Cursor cursor=db1.rawQuery("Select * from accountDetails ",null,null);
                  while(cursor.moveToNext()) {
                  cursor.getColumnName(4);
               }
                   if(cursor.getCount()>1){
                       Toast.makeText(depositAmount.this, "Amount Deposited successfully", Toast.LENGTH_SHORT).show();
                       bal.setText("Available balance"+" "+deposit_amount);
                   }
                   if(TextUtils.isEmpty(deposit_amount)){
                       depositAmount.setError("deposit amount should not be empty");
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