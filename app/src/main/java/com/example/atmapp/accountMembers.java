package com.example.atmapp;

public class accountMembers {
    String accountNumber;
    String accountPin;
    String name;
    String location;
    accountMembers(){

    }
    accountMembers(String accountNumber,String accountPin,String name,String location ){
        this.accountNumber=accountNumber;
        this.accountPin=accountPin;
        this.name=name;
        this.location=location;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountPin() {
        return accountPin;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
