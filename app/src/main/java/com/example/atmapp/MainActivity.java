package com.example.atmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText accountNumber,pin;
    Button submit;
    public ArrayList<accountMembers> accountHolders =new ArrayList();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accountNumber=findViewById(R.id.accountNumber);
        pin=findViewById(R.id.accountPin);
        submit=findViewById(R.id.button1);
        jsonStuff();
        click();

    }
    public void click(){
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Activity2.class);
                if(TextUtils.isEmpty(accountNumber.getText().toString())){
                    accountNumber.setError("Account number is compulsory");
                }
                if(TextUtils.isEmpty(pin.getText().toString())){
                    pin.setError("pin is must");
                }
                boolean ans=false;
                for(int i=0;i<accountHolders.size();i++) {
                    if (String.valueOf(accountNumber.getText()).equals(accountHolders.get(i).accountNumber) && String.valueOf(pin.getText()).equals(accountHolders.get(i).accountPin)) {
                        ans=true;
                        startActivity(intent);
                    }
                }
                if(ans==false){
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
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