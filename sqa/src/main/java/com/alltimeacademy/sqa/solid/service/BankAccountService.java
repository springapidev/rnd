package com.alltimeacademy.sqa.solid.service;


import com.alltimeacademy.sqa.solid.model.BankAccount;
import com.alltimeacademy.sqa.solid.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * SOLID Principles:
 * Single Responsibility Principle (SRP): Each method is clearly responsible for a specific action (deposit, withdraw, create, delete).
 * Open/Closed Principle (OCP): The service class can be extended with more functionality (e.g., transfer funds) without modifying existing methods.
 * Dependency Inversion Principle (DIP): BankAccountService depends on the abstraction BankAccountRepository, not the concrete class.
 * @author Mohammad Rajaul Islam
 */

@Service
public class BankAccountService implements AccountOperationsService, AccountManagementService {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }
@Transactional
    @Override
    public BankAccount deposit(String accountNumber, double amount) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        account.setBalance(account.getBalance() + amount);
        return bankAccountRepository.save(account);
    }
    @Transactional
    @Override
    public BankAccount withdraw(String accountNumber, double amount) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return bankAccountRepository.save(account);
    }

    @Override
    public BankAccount createAccount(BankAccount account) {
        return bankAccountRepository.save(account);
    }

    // Method to retrieve a bank account by its account number
    public BankAccount getBankAccount(String accountNumber) {
        Optional<BankAccount> bankAccount = Optional.ofNullable(bankAccountRepository.findByAccountNumber(accountNumber));
        return bankAccount.orElse(null); // Return null if account is not found
    }


    // Method to update an existing bank account
    public BankAccount updateAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    // Method to delete a bank account by ID
    public void deleteAccount(Long accountId) {
        bankAccountRepository.deleteById(accountId);
    }

    // Method to get all bank accounts
    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

}
