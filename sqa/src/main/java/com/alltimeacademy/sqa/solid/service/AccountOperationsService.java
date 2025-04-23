package com.alltimeacademy.sqa.solid.service;


import com.alltimeacademy.sqa.solid.model.BankAccount;

public interface AccountOperationsService {

    BankAccount deposit(String accountNumber, double amount);

    BankAccount withdraw(String accountNumber, double amount);
}
