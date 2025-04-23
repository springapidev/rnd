package com.alltimeacademy.sqa.goodcode;

public class DepositOperation implements BankOperation{
    @Override
    public void perform(BankAccount account, double amount) {
        if(amount > 0){
            account.setBalance(account.getBalance()+amount);
            System.out.println("Deposited Amount "+amount);
        }else {
            System.out.println("Invalid deposit amount");
        }
    }
}
