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
    usersModelClass usersModelClass;
    String depositAmount;
    String availableBalance;

    public List<String> arraylist;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        accountNumber = findViewById(R.id.accountNumber);
        pin = findViewById(R.id.accountPin);
        confirmPin = findViewById(R.id.confirm_accountPin);
        signup = findViewById(R.id.button1);
        depositAmount="0";
        availableBalance="0";
        click();

    }

    public void click() {
              signup.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      dBhelper = new DBhelper(MainActivity.this);
                      boolean add = dBhelper.add(username.getText().toString(), accountNumber.getText().toString(), pin.getText().toString(), depositAmount, availableBalance);
                      if(add==true){
                          Toast.makeText(MainActivity.this, "Registration completed successfully", Toast.LENGTH_SHORT).show();
                      }
//
                      Intent intent = new Intent(MainActivity.this,loginActivity.class);
                      startActivity(intent);
                  }
              });
    }

}