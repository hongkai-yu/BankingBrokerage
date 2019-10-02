package test;

import model.DebitAccount;
import model.BankAccounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountsTest {

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
        DebitAccount a1 = new DebitAccount();
        DebitAccount a2 = new DebitAccount();

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
        bankAccounts.openAccount();
        assertEquals(bankAccounts.numberOfAccounts(), 1);
    }
}


