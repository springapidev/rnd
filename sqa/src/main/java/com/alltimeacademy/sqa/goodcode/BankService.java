package com.alltimeacademy.sqa.goodcode;

public class BankService {
    public void performOperation(BankAccount account, BankOperation operation, double amount){
        operation.perform(account,amount);
    }
}
