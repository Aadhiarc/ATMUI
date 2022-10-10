package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class withdrawAmount extends AppCompatActivity {
    public  TextInputEditText withdrawAmount;
    public  Button withdrawButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_amount);
        withdrawAmount = findViewById(R.id.withdraw_amount_edittext);
        withdrawButton = findViewById(R.id.withdraw_xml_button);
        click();
    }
    void click(){
        withdrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(withdrawAmount.this,withdrawAmount.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}