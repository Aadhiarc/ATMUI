package com.example.atmapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {


    public static final String ACCOUNT_DETAILS = "accountDetails";
    public static final String NAME = "name";
    public static final String COLUMN_ACCOUNT_NUMBER = "accountNumber";
    public static final String COLUMN_ACCOUNT_PIN = "accountPin";
    public static final String COLUMN_DEPOSIT_AMOUNT = "depositAmount";
    public static final String COLUMN_AVAILABLE_BALANCE = "availableBalance";
    public static final String USER_DETAILS_DB = "userDetails.db";
    public ArrayList<usersModelClass> usersModelClasses=new ArrayList<>();

    public DBhelper(Context context) {
        super(context,USER_DETAILS_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE " + ACCOUNT_DETAILS + "(" + NAME + " text ," + COLUMN_ACCOUNT_NUMBER + " text ," + COLUMN_ACCOUNT_PIN + " text," + COLUMN_DEPOSIT_AMOUNT + " text," + COLUMN_AVAILABLE_BALANCE + " text)";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public boolean add(String name, String accountNumber, String accountPin, String depositAmount, String availableBalance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,name);
        cv.put(COLUMN_ACCOUNT_NUMBER,accountNumber);
        cv.put(COLUMN_ACCOUNT_PIN,accountPin);
        cv.put(COLUMN_DEPOSIT_AMOUNT,depositAmount);
        cv.put(COLUMN_AVAILABLE_BALANCE,availableBalance);
        long account_details = sqLiteDatabase.insert(ACCOUNT_DETAILS, null, cv);
        return account_details != -1;
    }


}
