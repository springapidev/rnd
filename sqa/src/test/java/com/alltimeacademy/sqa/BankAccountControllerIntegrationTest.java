package com.alltimeacademy.sqa;

import com.alltimeacademy.sqa.solid.controller.BankAccountController;
import com.alltimeacademy.sqa.solid.model.BankAccount;
import com.alltimeacademy.sqa.solid.model.SavingsAccount;
import com.alltimeacademy.sqa.solid.service.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc  // Automatically configure MockMvc for testing
public class BankAccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc for simulating HTTP requests

    @Mock
    private BankAccountService bankAccountService;  // Mocked BankAccountService

    @InjectMocks
    private BankAccountController bankAccountController;  // Controller to be tested

    private SavingsAccount savingsAccount;

    @BeforeEach
    public void setUp() {
        // Initialize a savings account for each test
        savingsAccount = new SavingsAccount("12345", 1000.0,"Rajaul Islam",  0.05);
    }

    @Test
    public void testCreateAccountIntegration() throws Exception {
        // Mock the service to return the created account
        when(bankAccountService.createAccount(any())).thenReturn(savingsAccount);

        // Perform a POST request to create a new account
        mockMvc.perform(post("/api/bank/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"accountNumber\": \"12345\", \"accountHolderName\": \"Rajaul Islam\", \"balance\": 1000.00}"))
                .andExpect(status().isCreated())  // Expect the CREATED status
                .andExpect(jsonPath("$.accountNumber").value("12345"))  // Verify account number in the response
                .andExpect(jsonPath("$.accountHolderName").value("Rajaul Islam"));  // Verify account holder name
    }

//    @Test
//    public void testGetAccountIntegration() throws Exception {
//        // Mock the service to return the savings account
//        when(bankAccountService.getBankAccount("12345")).thenReturn(savingsAccount);
//
//        // Perform a GET request to retrieve the account
//        mockMvc.perform(get("/api/bank/12345"))
//                .andExpect(status().isOk())  // Expect the OK status
//                .andExpect(jsonPath("$.accountNumber").value("12345"))  // Verify account number in response
//                .andExpect(jsonPath("$.accountHolderName").value("Rajaul Islam"));  // Verify account holder name
//    }
//
//    @Test
//    public void testDepositIntegration() throws Exception {
//        // Mock the service to return the updated account after deposit
//        when(bankAccountService.deposit("12345", 500.00)).thenReturn(savingsAccount);
//
//        // Perform a POST request for deposit
//        mockMvc.perform(post("/api/bank/12345/deposit")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("amount", "500.00"))  // Correctly pass the 'amount' parameter
//                .andExpect(status().isOk())  // Expect the OK status
//                .andExpect(jsonPath("$.balance").value(1000.00));  // Verify the updated balance
//    }
//
//    @Test
//    public void testWithdrawIntegration() throws Exception {
//        // Mock the service to return the updated account after withdrawal
//        when(bankAccountService.withdraw("12345", 200.00)).thenReturn(savingsAccount);
//
//        // Perform a POST request for withdrawal
//        mockMvc.perform(post("/api/bank/12345/withdraw")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .param("amount", "200.00"))  // Correctly pass the 'amount' parameter
//                .andExpect(status().isOk())  // Expect the OK status
//                .andExpect(jsonPath("$.balance").value(1000.00));  // Verify the updated balance
//    }
//
//    @Test
//    public void testApplyInterestIntegration() throws Exception {
//        // Mock the service to return the updated account after applying interest
//        when(bankAccountService.getBankAccount("12345")).thenReturn(savingsAccount);
//        when(bankAccountService.updateAccount(savingsAccount)).thenReturn(savingsAccount);
//
//        // Perform a POST request to apply interest
//        mockMvc.perform(post("/api/bank/12345/applyInterest"))
//                .andExpect(status().isOk())  // Expect OK status
//                .andExpect(jsonPath("$.balance").value(1000.00));  // Verify the balance after interest application
//    }
//
    @Test
    public void testDeleteAccountIntegration() throws Exception {
        // Mock the service to return the account
        when(bankAccountService.getBankAccount("12345")).thenReturn(savingsAccount);

        // Perform a DELETE request to delete the account
        mockMvc.perform(delete("/api/bank/12345"))
                .andExpect(status().isNoContent());  // Expect NO_CONTENT status
    }
//
//    @Test
//    public void testGetAllAccountsIntegration() throws Exception {
//        // Prepare a list of accounts for testing (List<BankAccount> if that's the return type)
//        List<BankAccount> accounts = new ArrayList<>();
//        accounts.add(savingsAccount);
//
//        // Mock the service to return a list of accounts (using List<BankAccount> as return type)
//        when(bankAccountService.getAllAccounts()).thenReturn(accounts);
//
//        // Perform a GET request to retrieve all accounts
//        mockMvc.perform(get("/api/bank/accounts"))
//                .andExpect(status().isOk())  // Expect the OK status
//                .andExpect(jsonPath("$[0].accountNumber").value("12345"));  // Verify the account in the response
//    }

}
