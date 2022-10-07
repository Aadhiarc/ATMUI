package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText accountNumber,pin;
    Button submit;
    ArrayList<accountMembers> accountHolders =new ArrayList();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountNumber=findViewById(R.id.accountNumber);
        pin=findViewById(R.id.accountPin);
        submit=findViewById(R.id.button1);
        click();
        jsonStuff();
        accountHolders.get(0).
    }
    public void click(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activity2.class);
                startActivity(intent);
            }
        });
    }
    public void jsonStuff(){
       String json;
        try {
            InputStream inputStream = getAssets().open("accountDetails.json");
            int size = inputStream.available();
            byte[] store = new byte[size];
            inputStream.read(store);
            inputStream.close();
            json =new String(store,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jobj = (JSONObject) jsonArray.get(i);
                String accountNumber = (String) (jobj.get("accountNumber"));
                String accountPin = (String) (jobj.get("accountPin"));
                String name = (String) (jobj.get("name"));
                String location = (String) (jobj.get("location"));
                accountHolders.add(new accountMembers(accountNumber,accountPin,name,location));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch(JSONException e){

        }
    }

}