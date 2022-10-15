package com.example.atmapp;

public class usersModelClass {

    String name;
    String accountNumber;
    String accountPin;
    String depositAmount;
    String availableBalance;

    public usersModelClass(String name, String accountNumber, String accountPin, String depositAmount, String availableBalance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.accountPin = accountPin;
        this.depositAmount = depositAmount;
        this.availableBalance = availableBalance;
    }

    @Override
    public String toString() {
        return "usersModelClass{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountPin='" + accountPin + '\'' +
                ", depositAmount='" + depositAmount + '\'' +
                ", availableBalance='" + availableBalance + '\'' +
                '}';
    }

    public usersModelClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(String accountPin) {
        this.accountPin = accountPin;
    }

    public String getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(String depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(String availableBalance) {
        this.availableBalance = availableBalance;
    }
}
