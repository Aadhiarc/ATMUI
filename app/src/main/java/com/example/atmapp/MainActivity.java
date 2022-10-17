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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText username, accountNumber, pin, confirmPin;
    Button signup;
    DBhelper dBhelper;
    TextInputLayout layoutUsername,layoutAccountNumber,layoutPin,layoutConfirmPin;
    String depositAmount;
    String availableBalance;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        layoutUsername=findViewById(R.id.usernameLayout);
        layoutAccountNumber=findViewById(R.id.accountNumberLayout);
        layoutPin=findViewById(R.id.accountPinLayout);
        layoutConfirmPin=findViewById(R.id.confirm_account_PinLayout);
        username = findViewById(R.id.username);
        accountNumber = findViewById(R.id.accountNumber);
        pin = findViewById(R.id.accountPin);
        confirmPin = findViewById(R.id.confirm_accountPin);
        signup = findViewById(R.id.button1);
        depositAmount="0";
        availableBalance="0";
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validation()==true){
                    dBhelper = new DBhelper(MainActivity.this);
                    boolean add = dBhelper.add(username.getText().toString(), accountNumber.getText().toString(), pin.getText().toString(), depositAmount, availableBalance);
                    if(add==true){
                        Toast.makeText(MainActivity.this, "Registration completed successfully", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(MainActivity.this,loginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    boolean validation(){
        if(TextUtils.isEmpty(username.getText().toString())){
            layoutUsername.setError("user name should not b empty");
            return false;
        }
        if(TextUtils.isEmpty(accountNumber.getText().toString())){
            layoutAccountNumber.setError("account number should not be empty");
            return false;
        }
        if(TextUtils.isEmpty(pin.getText().toString())){
            layoutPin.setError("pin should not be empty");
            return false;
        }
        if(TextUtils.isEmpty(confirmPin.getText().toString())){
            layoutConfirmPin.setError("confirm pin should not be empty");
            return false;
        }
        if(pin.getText().toString().equals(confirmPin.getText().toString())){
            return true;
        }else{
            Toast.makeText(this, "pin and confirm is not matching", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}