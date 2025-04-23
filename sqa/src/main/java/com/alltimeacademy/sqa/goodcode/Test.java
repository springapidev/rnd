package com.alltimeacademy.sqa.goodcode;

import java.io.FilterOutputStream;

public class Test {
    public static void main(String[] args) {
        BankAccount account=new BankAccount(1000.00);
        BankService service=new BankService();
        //Perform Deposit
        BankOperation deposit = new DepositOperation();
        service.performOperation(account,deposit,500.0);
        System.out.println("Balance After Deposit: "+account.getBalance());
        // Perform withdrawal
        BankOperation withdrawal=new WithdrawOperation();
        service.performOperation(account,withdrawal,200.0);
        System.out.println("Balance after withdrawal "+account.getBalance());

        // Trying inavlid withdrawal
        service.performOperation(account,withdrawal,2500.0);//Insufficient Fund
    }
}
