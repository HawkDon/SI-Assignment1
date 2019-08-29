package com.oliverloenning;

public class User {
    private int balance = 1000;
    private int accountNumber = 1;


    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int addBalance(int money) {
        this.balance = this.balance + money;
        return this.balance;
    }

    public int removeBalance(int money) {
        this.balance = this.balance - money;
        return this.balance;
    }
}
