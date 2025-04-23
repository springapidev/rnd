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
public class CheckingAccount extends BankAccount {


    private double overdraftLimit;
    private double overdraftFee;

    public CheckingAccount(String accountNumber, double balance, String accountHolderName, double overdraftLimit) {
        super(accountNumber, balance, accountHolderName);
        this.overdraftLimit = overdraftLimit;
    }
    // Method to check if the withdrawal exceeds the overdraft limit
    public boolean isOverdraftAllowed(double amount) {
        return (getBalance() - amount) >= -overdraftLimit;
    }

    // Method to withdraw, applying overdraft if necessary
    public BankAccount withdrawWithOverdraft(double amount) {
        if (isOverdraftAllowed(amount)) {
            setBalance(getBalance() - amount);
            if (getBalance() < 0) {
                setBalance(getBalance() - overdraftFee); // Apply overdraft fee if balance goes negative
            }
        } else {
            throw new IllegalArgumentException("Insufficient funds including overdraft limit");
        }
        return this;
    }

    // Method to deposit funds and reduce any overdraft fees if applicable
    public BankAccount depositWithOverdraftAdjustment(double amount) {
        setBalance(getBalance() + amount);
        if (getBalance() > 0 && overdraftFee > 0) {
            setBalance(getBalance() + overdraftFee); // Return overdraft fee if balance becomes positive
        }
        return this;
    }

    // Getters and setters for overdraft properties
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftFee() {
        return overdraftFee;
    }

    public void setOverdraftFee(double overdraftFee) {
        this.overdraftFee = overdraftFee;
    }
}
