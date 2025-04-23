package com.alltimeacademy.sqa;


import com.alltimeacademy.sqa.solid.model.BankAccount;
import com.alltimeacademy.sqa.solid.model.SavingsAccount;
import com.alltimeacademy.sqa.solid.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        // Set up a bank account for testing
        bankAccount = new SavingsAccount("12345", 1000.0,"Rajaul Islam",  0.05);
    }

    @Test
    public void testCreateBankAccount() {
        // Save the bank account
        bankAccount = bankAccountRepository.save(bankAccount);

        // Retrieve the account
        BankAccount fetchedAccount = bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber());

        // Assert that the saved account is the same as the fetched account
        assertThat(fetchedAccount).isNotNull();
        assertThat(fetchedAccount.getAccountNumber()).isEqualTo(bankAccount.getAccountNumber());
        assertThat(fetchedAccount.getAccountHolderName()).isEqualTo(bankAccount.getAccountHolderName());
        assertThat(fetchedAccount.getBalance()).isEqualTo(bankAccount.getBalance());
    }

    @Test
    public void testFindByAccountNumber() {
        // Save a new bank account
        bankAccount = bankAccountRepository.save(bankAccount);

        // Fetch account by account number
        BankAccount fetchedAccount = bankAccountRepository.findByAccountNumber("12345");

        // Assert that the account is correctly retrieved
        assertThat(fetchedAccount).isNotNull();
        assertThat(fetchedAccount.getAccountNumber()).isEqualTo("12345");
    }

    @Test
    public void testFindByNonExistentAccountNumber() {
        // Attempt to fetch an account that doesn't exist
        BankAccount fetchedAccount = bankAccountRepository.findByAccountNumber("99999");

        // Assert that the fetched account is null
        assertThat(fetchedAccount).isNull();
    }

    @Test
    public void testDeleteBankAccount() {
        // Save the bank account
        bankAccount = bankAccountRepository.save(bankAccount);

        // Delete the bank account
        bankAccountRepository.delete(bankAccount);

        // Attempt to fetch the deleted account
        BankAccount fetchedAccount = bankAccountRepository.findByAccountNumber(bankAccount.getAccountNumber());

        // Assert that the account was successfully deleted
        assertThat(fetchedAccount).isNull();
    }
}
