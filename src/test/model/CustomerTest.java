package model;

import model.exception.NoSuchAccountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.console.OptionsGenerator;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {


    private Customer customer;

    @BeforeEach
    void runBefore() {
        customer = new Customer("Unnamed", "123456");
    }

    @Test
    void testSetUserName() {
        assertEquals("Unnamed", customer.getUserName());
        customer.setUserName("User");
        assertEquals("User", customer.getUserName());
    }

    @Test
    void testPassword() {
        assertEquals("123456", customer.getPassword());
        assertTrue(customer.checkPassword("123456"));
        assertFalse(customer.checkPassword("123"));

        customer.setPassword("123");
        assertTrue(customer.checkPassword("123"));
    }

    @Test
    void testAddRemoveAccount() {
        assertEquals(0, customer.numberOfAccounts());
        DebitAccount a1 = new DebitAccount("a1");
        CreditAccount a2 = new CreditAccount("a2");

        assertNull(a1.getCustomer());
        assertNull(a2.getCustomer());

        assertTrue(customer.addAccount(a1));
        assertEquals(1, customer.numberOfAccounts());
        assertTrue(customer.hasAccount(a1));

        assertEquals(a1.getCustomer(), customer);

        assertFalse(customer.addAccount(a1));
        assertEquals(1, customer.numberOfAccounts());

        assertTrue(customer.addAccount(a2));
        assertEquals(2, customer.numberOfAccounts());
        assertTrue(customer.hasAccount(a2));

        assertEquals(a2.getCustomer(), customer);

        assertTrue(customer.removeAccount(a1));
        assertEquals(1, customer.numberOfAccounts());
        assertTrue(customer.hasAccount(a2));

        assertNull(a1.getCustomer());

        assertTrue(customer.removeAccount(a2));
        assertEquals(0, customer.numberOfAccounts());
        assertFalse(customer.hasAccount(a2));

        assertNull(a1.getCustomer());

        assertFalse(customer.removeAccount(a1));
    }

    @Test
    void testGetAccountByName() {
        Account a1 = new DebitAccount("a1");
        customer.addAccount(a1);

        try {
            assertEquals(a1, customer.getAccountByName("a1"));
        } catch (NoSuchAccountException e) {
            fail();
        }

        try {
            customer.getAccountByName("a4");
            fail();
        } catch (NoSuchAccountException e) {
            //expected
        }

        try {
            assertTrue(customer.removeAccount("a1"));
        } catch (NoSuchAccountException e) {
            fail();
        }

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

//    @Test
//    void testOpenAccountExceptions() {
//        try {
//            customer.openAccount("1", "A1");
//        } catch (Exception e) {
//            fail();
//        }
//
//        try {
//            customer.openAccount("3", "A1");
//            fail("Should have throw InvalidOperation.");
//        } catch (InvalidOperation e) {
//            // expected
//        } catch (DuplicationException e) {
//            fail();
//        }
//
//        try {
//            customer.openAccount("1", "A1");
//            fail("Should have throw DuplicateAccounts.");
//        } catch (InvalidOperation invalidOperation) {
//            fail();
//        } catch (DuplicationException duplicationException) {
//            //expected
//        }
//
//    }

    @Test
    void testOptionsOfCustomer() {
        assertEquals("[1] Open An Account [2] Remove An Account [3] Operate An Account [4] Save ",
                OptionsGenerator.generateOptions(customer.getOptions()));
    }
}


