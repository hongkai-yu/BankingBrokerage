package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account d0;
    private Account c0;
    private Account d1;
    private Account c1;

    @BeforeEach
    void setup() {
        d0 = new DebitAccount("d0");
        c0 = new CreditAccount("d0");
        d1 = new DebitAccount("d1", 200, 0.05);
        c1 = new CreditAccount("c1", 100);
    }

    @Test
    void testEqualsHashCode() {
        assertEquals(d0, d0);
        assertEquals(c0, c0);

        assertEquals(d0, new DebitAccount("d0", 1000));
        assertEquals(c0, new CreditAccount("d0",200));

        assertNotEquals(d0, c0);

        Account d10 = new DebitAccount("d1", 200, 0);
        Account c10 = new CreditAccount("c1",100);

        assertEquals(d1.hashCode(), d10.hashCode());
        assertEquals(d1, d10);

        assertEquals( c1.hashCode(), c10.hashCode());
        assertEquals(c1, c10);
    }

    @Test
    void testSetName() {
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

//    @Test
//    void testSaveAccountLine() throws IOException {
//        assertEquals("Debit Unnamed 0.0 0.0", d0.saveAccountLine());
//        assertEquals("Credit Unnamed 0.0", c0.saveAccountLine());
//        assertEquals("Debit d1 200.0 0.05", d1.saveAccountLine());
//        assertEquals("Credit c1 100.0", c1.saveAccountLine());
//    }

    @Test
    void testAccountInformation() {
        assertEquals("Account Type: Debit\n"
                        + "Account Name: d1\n"
                        + "Balance: 200.0\n",
                d1.accountInformation());
        assertEquals("Account Type: Credit\n"
                        + "Account Name: c1\n"
                        + "Balance: 0.0\n",
                c1.accountInformation());
    }

}
