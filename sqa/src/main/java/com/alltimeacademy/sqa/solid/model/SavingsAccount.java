package com.alltimeacademy.sqa.solid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SOLID Principles:
 * Liskov Substitution Principle (LSP): These subclasses (SavingsAccount, CheckingAccount) can be substituted wherever a BankAccount is expected, without breaking the functionality.
 * @author Mohammad Rajaul Islam
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class SavingsAccount extends BankAccount{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, String accountHolderName, double interestRate) {
        super(accountNumber, balance, accountHolderName);
        this.interestRate = interestRate;
    }
    // Method to calculate interest based on the balance
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    // Method to apply interest to the account
    public void applyInterest() {
        double interest = calculateInterest();
        setBalance(getBalance() + interest);
    }

    // Method to deposit interest
    public BankAccount depositInterest() {
        double interest = calculateInterest();
        setBalance(getBalance() + interest);
        return this;
    }

    // Getters and setters for interestRate
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
