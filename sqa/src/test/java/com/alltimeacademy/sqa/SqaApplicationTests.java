package com.alltimeacademy.sqa;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class SqaApplicationTests {

//    @Test
//    void testAll() {
//        bankAccountRepositoryTest();
//
//    }
    @Test
    void bankAccountRepositoryTest() {
    BankAccountRepositoryTest bankAccountRepositoryTest=new BankAccountRepositoryTest();
    bankAccountRepositoryTest.testCreateBankAccount();
    bankAccountRepositoryTest.testFindByAccountNumber();
    bankAccountRepositoryTest.testFindByNonExistentAccountNumber();
    bankAccountRepositoryTest.testDeleteBankAccount();
    }

}
