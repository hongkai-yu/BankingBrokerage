package model;

import model.exception.UserNameOrPasswordWrongException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    private static final String TEST_FILE_PATH = "./data/testBank.txt";

    private Bank bank;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;

    @BeforeEach
    void setUp() {
        bank = new Bank();
        customer1 = new Customer("c","123");
        customer2 = new Customer("cus","123");
        customer3 = new Customer("c","456");
    }

    @Test
    void testDuplicateCustomer() {
        assertEquals(customer1, customer3);
        assertNotEquals(customer1, customer2);
    }

    @Test
    void testAddRemoveCustomer() {
        assertTrue(bank.addCustomer(customer1));
        assertEquals(bank,customer1.getBank());
        assertFalse(bank.addCustomer(customer3));

        assertTrue(bank.removeCustomer(customer1));

    }

    @Test
    void testSignUpLogInCustomer() {
        assertTrue(bank.signUpCustomer("c","123"));
        assertTrue(bank.getCustomers().contains(customer1));

        assertFalse(bank.signUpCustomer("c","234"));

        try {
            assertEquals(customer1,bank.logInCustomer("c", "123"));
        } catch (UserNameOrPasswordWrongException e) {
            fail();
        }

        try {
            bank.logInCustomer("c","234");
            fail();
        } catch (UserNameOrPasswordWrongException e) {
            //expected;
        }

        try {
            bank.logInCustomer("b","123");
            fail();
        } catch (UserNameOrPasswordWrongException e) {
           //expected
        }
    }

    @Test
    void testSaveLoad() {
        bank.addCustomer(customer1);

        try {
            bank.save(TEST_FILE_PATH);
        } catch (IOException e) {
           fail();
        }

        try {
            Bank loadBank = Bank.load(TEST_FILE_PATH);
            assertTrue(loadBank.getCustomers().contains(customer1));
        } catch (IOException | ClassNotFoundException e) {
           fail();
        }
    }

    @Test
    void testObserver() {
        bank.addCustomer(customer1);
        assertEquals(1, bank.countObservers());
        DebitAccount debitAccount = new DebitAccount("Debit", 1000, 0.02);
        customer1.addAccount(debitAccount);

        bank.setInterestRate(0.01);
        assertEquals(bank.getInterestRate(), debitAccount.getInterestRate());
    }
}