package com.alltimeacademy.sqa;

import com.alltimeacademy.sqa.solid.model.BankAccount;
import com.alltimeacademy.sqa.solid.model.SavingsAccount;
import com.alltimeacademy.sqa.solid.repository.BankAccountRepository;
import com.alltimeacademy.sqa.solid.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService bankAccountService;  // The service we're testing

    private SavingsAccount savingsAccount;

    @BeforeEach
    public void setUp() {
        savingsAccount =  new SavingsAccount("12345", 1000.0,"Rajaul Islam",  0.05);
    }

    @Test
    public void testDeposit() {
        // Arrange: Mocking the repository
        when(bankAccountRepository.findByAccountNumber("12345")).thenReturn(savingsAccount);
        when(bankAccountRepository.save(savingsAccount)).thenReturn(savingsAccount);

        // Act: Call the method to be tested
        bankAccountService.deposit("12345", 500.00);

        // Assert: Verify the behavior
        assertEquals(1500.00, savingsAccount.getBalance());
        verify(bankAccountRepository, times(1)).save(savingsAccount);  // Ensure save is called once
    }

    @Test
    public void testWithdrawSuccess() {
        // Arrange: Mock the repository
        when(bankAccountRepository.findByAccountNumber("12345")).thenReturn(savingsAccount);
        when(bankAccountRepository.save(savingsAccount)).thenReturn(savingsAccount);

        // Act: Call the withdraw method
        bankAccountService.withdraw("12345", 200.00);

        // Assert: Verify the behavior
        assertEquals(800.00, savingsAccount.getBalance());
        verify(bankAccountRepository, times(1)).save(savingsAccount);  // Ensure save is called once
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        // Arrange: Mock the repository with insufficient balance
        when(bankAccountRepository.findByAccountNumber("12345")).thenReturn(savingsAccount);

        // Act & Assert: Ensure an exception is thrown due to insufficient balance
        assertThrows(RuntimeException.class, () -> bankAccountService.withdraw("12345", 2000.00),
                "Insufficient balance");
        verify(bankAccountRepository, times(0)).save(savingsAccount);  // Ensure save is not called
    }

    @Test
    public void testGetBankAccount() {
        // Arrange: Mock the repository
        when(bankAccountRepository.findByAccountNumber("12345")).thenReturn(savingsAccount);

        // Act: Call the method to be tested
        BankAccount retrievedAccount = bankAccountService.getBankAccount("12345");

        // Assert: Verify the result
        assertNotNull(retrievedAccount);
        assertEquals("12345", retrievedAccount.getAccountNumber());
        assertEquals("Rajaul Islam", retrievedAccount.getAccountHolderName());
    }
}
