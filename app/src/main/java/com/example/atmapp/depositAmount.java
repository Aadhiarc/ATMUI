package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class depositAmount extends AppCompatActivity {
    public  TextInputEditText depositAmount;
    public  Button depositButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_amount);
        depositAmount = findViewById(R.id.deposit_amount_edittext);
        depositButton = findViewById(R.id.deposit_xml_button);
        click();
    }
    void click(){
        depositButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(depositAmount.getText().toString()))
                {
                    depositAmount.setError("amount is empty");
                }
                if(Double.parseDouble(depositAmount.getText().toString())>0) {
                    Toast.makeText(depositAmount.this, "amount deposited successfully" + " " + depositAmount.getText(), Toast.LENGTH_SHORT).show();
                }
                if(Double.parseDouble(depositAmount.getText().toString())<=0){
                    Toast.makeText(depositAmount.this, "deposit amount should be positive", Toast.LENGTH_SHORT).show();
                }
                }
        });
    }
}