package com.alltimeacademy.sqa.solid.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * SOLID Principles:
 * Single Responsibility Principle (SRP): This class only represents the data of a bank account.
 * Open/Closed Principle (OCP): This class can be extended to add new account types without changing the existing code.
 * @author Mohammad Rajkaul Islam
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version  // This will apply versioning for the entire entity hierarchy
    private Long version;  // Version field for optimistic locking
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private String accountHolderName;

    public BankAccount(String accountNumber, double balance, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolderName = accountHolderName;
    }
}
