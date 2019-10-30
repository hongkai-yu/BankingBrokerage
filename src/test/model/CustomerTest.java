package model;

import model.exception.DuplicateAccounts;
import model.exception.InvalidOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private static final String TEST_FILE_PATH = "./data/testBankAccounts.txt";

    private Customer customer;

    @BeforeEach
    void runBefore() {
        customer = new Customer();
    }

    @Test
    void testSetUserName() {
        assertEquals("Unnamed", customer.getUserName());
        customer.setUserName("User");
        assertEquals("User", customer.getUserName());
    }

    @Test
    void testAddRemoveAccount() {
        assertEquals(0, customer.numberOfAccounts());
        DebitAccount a1 = new DebitAccount("a1");
        CreditAccount a2 = new CreditAccount("a2");

        assertTrue(a1.getCustomer() == null);
        assertTrue(a2.getCustomer() == null);

        assertTrue(customer.addAccount(a1));
        assertEquals(1, customer.numberOfAccounts());
        assertTrue(customer.getAccountsMap().containsValue(a1));

        assertTrue(a1.getCustomer().equals(customer));

        assertFalse(customer.addAccount(a1));
        assertEquals(1, customer.numberOfAccounts());

        assertTrue(customer.addAccount(a2));
        assertEquals(2, customer.numberOfAccounts());
        assertTrue(customer.getAccountsMap().containsValue(a2));

        assertTrue(a2.getCustomer().equals(customer));

        assertTrue(customer.removeAccount(a1));
        assertEquals(1, customer.numberOfAccounts());
        assertTrue(customer.getAccountsMap().containsValue(a2));

        assertTrue(a1.getCustomer() == null);

        assertTrue( customer.removeAccount(a2.getName()));
        assertEquals(0, customer.numberOfAccounts());
        assertFalse(customer.getAccountsMap().containsValue(a2));

        assertTrue(a1.getCustomer() == null);

        assertFalse(customer.removeAccount(a1.getName()));
    }

    @Test
    void testOpenAccount() {
        try {
            customer.openAccount("1", "A1");
        } catch (Exception e) {
            fail();
        }
        assertEquals(1, customer.numberOfAccounts());

        try {
            customer.openAccount("2", "A2");
        } catch (Exception e) {
            fail();
        }
        assertEquals(2, customer.numberOfAccounts());
    }

    @Test
    void testOpenAccountExceptions() {
        try {
            customer.openAccount("1", "A1");
        } catch (Exception e) {
            fail();
        }

        try {
            customer.openAccount("3", "A1");
            fail("Should have throw InvalidOperation.");
        } catch (InvalidOperation e) {
            // expected
        } catch (DuplicateAccounts e) {
            fail();
        }

        try {
            customer.openAccount("2", "A1");
            fail("Should have throw DuplicateAccounts.");
        } catch (InvalidOperation invalidOperation) {
            fail();
        } catch (DuplicateAccounts duplicateAccounts) {
            //expected
        }

    }

    @Test
    void testSaveAndLoadAccounts() throws IOException {
        customer.addAccount(new CreditAccount("C1", 200));
        customer.saveAccounts(TEST_FILE_PATH);

        Customer loadedCustomer1 = new Customer();
        loadedCustomer1.loadAccounts(TEST_FILE_PATH);
        assertEquals(customer.getUserName(), loadedCustomer1.getUserName());
        assertEquals(customer.getAccountsMap().size(), loadedCustomer1.getAccountsMap().size());

//        int size = loadedBankAccounts1.accounts.size();
//        for (int i = 0; i <= size; i++) {
//            assertTrue(loadedBankAccounts1.accounts.get(i).equals(bankAccounts.accounts.get(i)));
//        }

    }


}


