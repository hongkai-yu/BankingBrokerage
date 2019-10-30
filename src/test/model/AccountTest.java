package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {

    Account d0;
    Account c0;
    Account d1;
    Account c1;

    @BeforeEach
    void setup() {
        d0 = new DebitAccount();
        c0 = new CreditAccount();
        d1 = new DebitAccount("d1", 200, 0.05);
        c1 = new CreditAccount("c1", 100);
    }

    @Test
    void testEqualsHashCode() {
        assertTrue(d0.equals((d0)));
        assertTrue(c0.equals((c0)));

        assertTrue(d0.equals(new DebitAccount()));
        assertTrue(c0.equals(new CreditAccount()));

        assertFalse(d0.equals(c0));

        Account d10 = new DebitAccount("d1", 200, 0);
        Account c10 = new CreditAccount("c1",100);

        assertFalse(d1.hashCode() - d10.hashCode() == 0);
        assertFalse(d1.equals(d10));

        assertTrue(c1.hashCode() - c10.hashCode() == 0);
        assertTrue(c1.equals(c10));
    }

    @Test
    void testSetName() {
        assertEquals("Unnamed", d0.getName());
        d0.setName("RealName");
        assertEquals("RealName", d0.getName());
    }

    @Test
    void testAddSubBalance() {
        assertEquals(0, c0.getBalance());
        c0.addBalance(50);
        c0.subBalance(30);
        assertEquals(20, c0.getBalance());
    }

    @Test
    void testSaveAccountLine() throws IOException {
        assertEquals("Debit Unnamed 0.0 0.0", d0.saveAccountLine());
        assertEquals("Credit Unnamed 0.0", c0.saveAccountLine());
        assertEquals("Debit d1 200.0 0.05", d1.saveAccountLine());
        assertEquals("Credit c1 100.0", c1.saveAccountLine());
    }

    @Test
    void testAccountInformation() {
        assertEquals("Account Type: Debit\n"
                        + "Account Name: d1\n"
                        + "Balance: 200.0\n",
                d1.accountInformation());
        assertEquals("Account Type: Credit\n"
                        + "Account Name: c1\n"
                        + "Balance: 100.0\n",
                c1.accountInformation());
    }

    @Test
    void testOptionsOfAccount() {
        assertEquals("[1] Change Name [2] Deposit [3] Withdraw ", d1.optionsOfAccount());
        assertEquals("[1] Change Name [2] Make Purchase ", c1.optionsOfAccount());
    }

}
