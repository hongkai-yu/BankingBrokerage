package test;

import model.Account;
import model.BankAccounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.PackedColorModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestBankAccounts {

    private BankAccounts bankAccounts;

    @BeforeEach
    public void runBefore() {
        bankAccounts = new BankAccounts();
    }

    @Test
    public void testSetUserName() {
        assertEquals(bankAccounts.getUserName(), "Unnamed");
        bankAccounts.setUserName("User");
        assertEquals(bankAccounts.getUserName(), "User");
    }

    @Test
    public void addAndRemoveAccount() {
        assertEquals(bankAccounts.numberOfAccounts(), 0);
        Account a1 = new Account();
        Account a2 = new Account();

        bankAccounts.addAccount(a1);
        assertEquals(bankAccounts.numberOfAccounts(), 1);
        assertTrue(bankAccounts.accounts.contains(a1));

        bankAccounts.addAccount(a2);
        assertEquals(bankAccounts.numberOfAccounts(), 2);
        assertTrue(bankAccounts.accounts.contains(a2));

        bankAccounts.removeAccount(a1);
        assertEquals(bankAccounts.numberOfAccounts(), 1);
        assertTrue(bankAccounts.accounts.contains(a2));
    }

    public void testOpenDebitAccount() {
        assertEquals(bankAccounts.numberOfAccounts(), 0);
        bankAccounts.openDebitAccount();
        assertEquals(bankAccounts.numberOfAccounts(), 1);
    }
}


