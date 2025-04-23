package com.alltimeacademy.sqa.solid.service;


import com.alltimeacademy.sqa.solid.model.BankAccount;

public interface AccountManagementService {

    BankAccount createAccount(BankAccount account);

    void deleteAccount(Long id);
}
