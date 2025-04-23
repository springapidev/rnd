package com.alltimeacademy.sqa;

import com.alltimeacademy.sqa.solid.model.SavingsAccount;
import com.alltimeacademy.sqa.solid.repository.BankAccountRepository;
import com.alltimeacademy.sqa.solid.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BankAccountServiceIntegrationTest {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private SavingsAccount savingsAccount;

    @BeforeEach
    public void setUp() {
        // Initialize a savings account for each test
        savingsAccount =  new SavingsAccount("12345", 1000.0,"Rajaul Islam",  0.05);
        bankAccountRepository.save(savingsAccount);  // Save to the test database
    }

    @Test
    @Transactional
    public void testDepositIntegration() {
        // Act: Call the deposit method to deposit 500
        bankAccountService.deposit("12345", 500.00);

        // Fetch the account from the database after deposit
        SavingsAccount updatedAccount = (SavingsAccount) bankAccountRepository.findByAccountNumber("12345");

        // Assert: Verify the balance is updated
        assertEquals(1500.00, updatedAccount.getBalance());
    }

    @Test
    @Transactional
    public void testWithdrawIntegration() {
        // Act: Withdraw 200
        bankAccountService.withdraw("12345", 200.00);

        // Fetch the account from the database after withdrawal
        SavingsAccount updatedAccount = (SavingsAccount) bankAccountRepository.findByAccountNumber("12345");

        // Assert: Verify the balance is updated
        assertEquals(800.00, updatedAccount.getBalance());
    }

    @Test
    @Transactional
    public void testWithdrawInsufficientBalance() {
        // Act & Assert: Try withdrawing more than the available balance and check for exception
        assertThrows(RuntimeException.class, () -> bankAccountService.withdraw("12345", 2000.00),
                "Insufficient balance");
    }

    @Test
    @Transactional
    public void testGetBankAccountIntegration() {
        // Act: Fetch the account using the service
        SavingsAccount retrievedAccount = (SavingsAccount) bankAccountService.getBankAccount("12345");

        // Assert: Verify the details are correct
        assertNotNull(retrievedAccount);
        assertEquals("12345", retrievedAccount.getAccountNumber());
        assertEquals("Rajaul Islam", retrievedAccount.getAccountHolderName());
        assertEquals(1000.00, retrievedAccount.getBalance());
    }

    @Test
    @Transactional
    public void testAccountPersistenceAfterOperations() {
        // Act: Perform deposit and withdrawal operations
        bankAccountService.deposit("12345", 500.00);
        bankAccountService.withdraw("12345", 200.00);

        // Fetch the account from the database
        SavingsAccount updatedAccount = (SavingsAccount) bankAccountRepository.findByAccountNumber("12345");

        // Assert: Verify the balance reflects the correct changes
        assertEquals(1300.00, updatedAccount.getBalance());
    }
}
