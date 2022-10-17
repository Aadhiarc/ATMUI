package com.example.atmapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Activity3 extends AppCompatActivity {
    Button bal_btn,withdraw_btn,deposit_btn,back;
    AlertDialog.Builder builder;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_3);
        bal_btn=(Button) findViewById(R.id.balance_button);
        withdraw_btn=(Button) findViewById(R.id.withdraw_button);
        deposit_btn=(Button) findViewById(R.id.deposit_button);
        back= (Button)findViewById(R.id.back_log);
        gate();
    }
    void gate(){
        deposit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity3.this,depositAmount.class);
                startActivity(intent);
            }
        });

        bal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(Activity3.this,viewBalance.class);
                startActivity(intent1);
                viewBalance b = new viewBalance();
                //b.balance.setText();
            }
        });

        withdraw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(Activity3.this,withdrawAmount.class);
                builder = new AlertDialog.Builder(Activity3.this);
                builder.setTitle("Alert !!!")
                        .setMessage("Do you want to logout")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Activity3.this,loginActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });builder.show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity3.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert !!!")
                .setMessage("Do you want to logout")
                .setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Activity3.this,loginActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });builder.show();

    }

}