package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class viewBalance extends AppCompatActivity {
    TextView balance;
    double balanceView=0.0;
    depositAmount d = new depositAmount();
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
        try {
            double deposit_balance = Double.parseDouble(getIntent().getStringExtra("depositAmount"));
            balance.setText((int) deposit_balance);
        }catch (Exception e){

        }

    }
}