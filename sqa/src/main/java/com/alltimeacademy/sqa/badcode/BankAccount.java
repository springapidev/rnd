package com.alltimeacademy.sqa.badcode;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }
    public void deposit(double amount){
        balance += amount;
    }
    public void withdraw(double amount){
        if(amount < balance){
            System.out.println("Error: Insufficient funds!");
            return;
        }
        balance -=amount;
    }

    public double getBalance() {
        return balance;
    }
}
