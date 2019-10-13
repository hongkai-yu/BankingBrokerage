package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountTest {

    Account d0;
    Account c0;
    Account d1;
    Account c1;

    @BeforeEach
    public void setup() {
        d0 = new DebitAccount();
        c0 = new CreditAccount();
        d1 = new DebitAccount("d1", 200, 0.05);
        c1 = new CreditAccount("c1", 100);
    }

    @Test
    public void testSaveAccountLine() throws IOException {
        assertEquals(d0.saveAccountLine(), "Debit Unnamed 0.0 0.0");
        assertEquals(c0.saveAccountLine(), "Credit Unnamed 0.0");
        assertEquals(d1.saveAccountLine(), "Debit d1 200.0 0.05");
        assertEquals(c1.saveAccountLine(), "Credit c1 100.0");
    }

    @Test
    public void testSetName() {
        assertEquals(d0.getName(), "Unnamed");
        d0.setName("RealName");
        assertEquals(d0.getName(), "RealName");
    }

    @Test
    public void testAddBalance() {
        assertEquals(c0.getBalance(), 0);
        c0.addBalance(50);
        assertEquals(c0.getBalance(), 50);
    }

    @Test
    public void testSubBalance() {
        assertEquals(d0.getBalance(), 0);
        d0.subBalance(50);
        assertEquals(d0.getBalance(), -50);
    }

    @Test
    public void testAccountInformation() {
        assertEquals(d1.accountInformation(),
                "Account Type: Debit\n"
                        + "Account Name: d1\n"
                        + "Balance: 200.0\n");
        assertEquals(c1.accountInformation(),
                "Account Type: Credit\n"
                        + "Account Name: c1\n"
                        + "Balance: 100.0\n");
    }

    @Test
    public void testOptionsOfAccount() {
        assertEquals(d1.optionsOfAccount(), "[1] Change name [2] Deposit [3] Withdraw");
        assertEquals(c1.optionsOfAccount(), "[1] Make Purchase");
    }

}
