package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {
    Button bal_btn,withdraw_btn,deposit_btn,exit_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        bal_btn=(Button) findViewById(R.id.balance_button);
        withdraw_btn=(Button) findViewById(R.id.withdraw_button);
        deposit_btn=(Button) findViewById(R.id.deposit_button);
        exit_btn=(Button) findViewById(R.id.exit_button);
        gate();
    }
    void gate(){
        deposit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this,depositAmount.class);
                startActivity(intent);
            }
        });

        bal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(Activity2.this,viewBalance.class);
                startActivity(intent1);
                viewBalance b = new viewBalance();
                //b.balance.setText();
            }
        });
        withdraw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Activity2.this,withdrawAmount.class);
                startActivity(intent2);
            }
        });
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }

}