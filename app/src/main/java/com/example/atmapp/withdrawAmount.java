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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class withdrawAmount extends AppCompatActivity {
    public  TextInputEditText withdrawAmount;
    public  Button withdrawButton;
    TextView avBalance;
    DBhelper dBhelper;
    List<String> arrayList;
    boolean condition;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_withdraw_amount);
        withdrawAmount = findViewById(R.id.withdraw_amount_edittext);
        withdrawButton = findViewById(R.id.view_xml_backButton);
        avBalance=findViewById(R.id.viewbalanceWithdraw);
        database();
        click();
    }
    void click(){
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                condition=true;
                SQLiteDatabase db =dBhelper.getReadableDatabase();
                Cursor cursor1=db.rawQuery("select * from accountDetails",null,null);
                arrayList=new ArrayList<String>();
                while (cursor1.moveToNext()){
                    arrayList.add(cursor1.getString(1));
                    arrayList.add(cursor1.getString(4));
                }
                SharedPreferences mysharedPreferences=getSharedPreferences("Aadhi",MODE_PRIVATE);
                String acNum=mysharedPreferences.getString("accountNumber","empty");
                for(int i =0;i<arrayList.size();i++){
                    if(arrayList.get(i).equals(acNum)){
                        for(int j =0;j<arrayList.size();j++) {
                            Double withdraw =Double.parseDouble(withdrawAmount.getText().toString());
                            Double updateBal = Double.parseDouble(arrayList.get(j))-withdraw;
                            avBalance.setText(updateBal.toString());
                            Intent intent = new Intent(com.example.atmapp.withdrawAmount.this,viewBalance.class);
                            String updatedBal = updateBal.toString();
                            intent.putExtra("updatedBalance",updatedBal);
                            intent.putExtra("checking",condition);
                        }
                    }
                }
            }
        });
    }

    void database(){
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
                    System.out.println("MY BAL" + updateBal);
                    avBalance.setText(updateBal);
                }
            }
        }
    }
}