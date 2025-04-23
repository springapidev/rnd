package com.alltimeacademy.sqa.goodcode;

public class WithdrawOperation implements BankOperation{
    @Override
    public void perform(BankAccount account, double amount) {
        if(amount <= 0){
            System.out.println("Invalid withdrawal amount");
        }else if (amount > account.getBalance()){
            System.out.println("Error: Insifficient Funds");
        }else {
            account.setBalance(account.getBalance()+amount);
            System.out.println("Withdrawal amount");
        }
    }
}
