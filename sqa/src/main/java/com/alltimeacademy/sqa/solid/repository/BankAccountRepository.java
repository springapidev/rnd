package com.alltimeacademy.sqa.solid.repository;

import com.alltimeacademy.sqa.solid.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * SOLID Principles:
 * Single Responsibility Principle (SRP): The repository is responsible solely for data persistence operations.
 * Open/Closed Principle (OCP): You can add new custom queries without modifying existing methods.
 * @author Mohammad Rajkaul Islam
 */
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    // Custom query methods if needed
    BankAccount findByAccountNumber(String accountNumber);
}
