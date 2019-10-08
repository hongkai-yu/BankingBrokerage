package test;

import model.Account;
import model.CreditAccount;
import model.DebitAccount;
import model.BankAccounts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.BankDemo;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankAccountsTest {

    private static final String TEST_FILE_PATH = "./data/testBankAccounts.txt";

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
    public void testAddAndRemoveAccount() {
        assertEquals(bankAccounts.numberOfAccounts(), 0);
        DebitAccount a1 = new DebitAccount();
        DebitAccount a2 = new DebitAccount();

        bankAccounts.addAccount(a1);
        assertEquals(bankAccounts.numberOfAccounts(), 1);
        assertTrue(bankAccounts.getAccounts().contains(a1));

        bankAccounts.addAccount(a2);
        assertEquals(bankAccounts.numberOfAccounts(), 2);
        assertTrue(bankAccounts.getAccounts().contains(a2));

        bankAccounts.removeAccount(a1);
        assertEquals(bankAccounts.numberOfAccounts(), 1);
        assertTrue(bankAccounts.getAccounts().contains(a2));

        bankAccounts.removeAccount(0);
        assertEquals(bankAccounts.numberOfAccounts(), 0);
        assertFalse(bankAccounts.getAccounts().contains(a2));

    }

    @Test
    public void testOpenAccountOptions() {
        bankAccounts.openAccountOptions("1");
        assertEquals(bankAccounts.numberOfAccounts(),1);

        bankAccounts.openAccountOptions("2");
        assertEquals(bankAccounts.numberOfAccounts(),2);
    }

    @Test
    public void testSaveAndLoadAccounts() throws IOException {
        bankAccounts.addAccount(new CreditAccount("C1", 200));
        bankAccounts.saveAccounts(TEST_FILE_PATH);

        BankAccounts loadedBankAccounts1 = new BankAccounts();
        loadedBankAccounts1.loadAccounts(TEST_FILE_PATH);
        assertEquals(loadedBankAccounts1.getUserName(), bankAccounts.getUserName());
        assertEquals(loadedBankAccounts1.getAccounts().size(),bankAccounts.getAccounts().size());

//        int size = loadedBankAccounts1.accounts.size();
//        for (int i = 0; i <= size; i++) {
//            assertTrue(loadedBankAccounts1.accounts.get(i).equals(bankAccounts.accounts.get(i)));
//        }

    }
}


