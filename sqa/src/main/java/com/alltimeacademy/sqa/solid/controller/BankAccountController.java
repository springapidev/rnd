package com.alltimeacademy.sqa.solid.controller;

import com.alltimeacademy.sqa.solid.model.BankAccount;
import com.alltimeacademy.sqa.solid.model.SavingsAccount;
import com.alltimeacademy.sqa.solid.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    // Create a new bank account
    @PostMapping("/create")
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdAccount = bankAccountService.createAccount(bankAccount);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // Get a bank account by account number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
        BankAccount bankAccount = bankAccountService.getBankAccount(accountNumber);
        if (bankAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    // Deposit money into an account
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<BankAccount> deposit(@PathVariable String accountNumber, @RequestParam double amount) {
        try {
            BankAccount updatedAccount = bankAccountService.deposit(accountNumber, amount);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Withdraw money from an account
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<BankAccount> withdraw(@PathVariable String accountNumber, @RequestParam double amount) {
        try {
            BankAccount updatedAccount = bankAccountService.withdraw(accountNumber, amount);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Apply interest to a savings account
    @PostMapping("/{accountNumber}/applyInterest")
    public ResponseEntity<BankAccount> applyInterest(@PathVariable String accountNumber) {
        BankAccount bankAccount = bankAccountService.getBankAccount(accountNumber);
        if (bankAccount instanceof SavingsAccount) {
            ((SavingsAccount) bankAccount).applyInterest();
            bankAccountService.updateAccount(bankAccount);
            return new ResponseEntity<>(bankAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Delete an account
    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountNumber) {
        BankAccount bankAccount = bankAccountService.getBankAccount(accountNumber);
        if (bankAccount != null) {
            bankAccountService.deleteAccount(bankAccount.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get all bank accounts
    @GetMapping("/accounts")
    public ResponseEntity<List<BankAccount>> getAllAccounts() {
        List<BankAccount> accounts = bankAccountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
