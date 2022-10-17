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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class withdrawAmount extends AppCompatActivity {
    public TextInputEditText withdrawAmount;
    public Button withdrawButton;
    TextView avBalance;
    DBhelper dBhelper;
    String view_balance;
    int editViewText;
    int dataBase;
    AlertDialog.Builder builder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_withdraw_amount);
        withdrawAmount = findViewById(R.id.withdraw_amount_edittext);
        withdrawButton = findViewById(R.id.view_xml_backButton);
        avBalance = findViewById(R.id.viewbalanceWithdraw);
       withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert();
            }
        });
        dbRead();
    }

    void dbRead() {
        SharedPreferences sp = getApplicationContext().getSharedPreferences("localdb", MODE_PRIVATE);
        String acnum = sp.getString("loginAccountNumber", "");
        dBhelper = new DBhelper(withdrawAmount.this);
        SQLiteDatabase sqLiteDatabase = dBhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + ACCOUNT_DETAILS + " where " + COLUMN_ACCOUNT_NUMBER + "=?", new String[]{acnum});
        while (cursor.moveToNext()) {
            view_balance = cursor.getString(3);
            avBalance.setText(view_balance);
        }
    }

     void alert(){
         builder = new AlertDialog.Builder(this);
         builder.setTitle("Deposit confirmation").setMessage("Do you want to withdraw amount ?").setCancelable(false)
                 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         Toast.makeText(withdrawAmount.this, "Amount withdrawn successfully", Toast.LENGTH_SHORT).show();
                         dBhelper = new DBhelper(withdrawAmount.this);
                         SharedPreferences sp = getApplicationContext().getSharedPreferences("localdb", MODE_PRIVATE);
                         String acnum = sp.getString("loginAccountNumber", "");
                         SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
                         ContentValues contentValues = new ContentValues();
                         editViewText = Integer.parseInt(withdrawAmount.getText().toString());
                         dataBase = Integer.parseInt(view_balance);
                         if (dataBase>=editViewText) {
                             int remainingBalance = dataBase - editViewText;
                             String string_remaining_balance = String.valueOf(remainingBalance);
                             contentValues.put("depositAmount", string_remaining_balance);
                             sqLiteDatabase.update(ACCOUNT_DETAILS, contentValues, COLUMN_ACCOUNT_NUMBER + " =? ", new String[]{acnum});
                             avBalance.setText(string_remaining_balance);
                             dbRead();
                         }
                         else {
                             Toast.makeText(withdrawAmount.this, "insufficient balance", Toast.LENGTH_SHORT).show();
                         }
                     }
                 }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         dialogInterface.cancel();
                     }
                 }).show();
     }

}

