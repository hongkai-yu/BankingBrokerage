package model;

import model.investment.InvestmentAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account d0;
    private Account c0;
    private Account d1;
    private Account c1;
    private Account i1;

    @BeforeEach
    void setup() {
        d0 = new DebitAccount("d0");
        c0 = new CreditAccount("d0");
        d1 = new DebitAccount("d1", 200, 0.05);
        c1 = new CreditAccount("c1", 100);
        i1 = new InvestmentAccount("i1", 100.0);
    }

    @Test
    void testEqualsHashCode() {
        assertEquals(d0, d0);
        assertEquals(c0, c0);

        assertEquals(d0, new DebitAccount("d0", 1000));
        assertEquals(c0, new CreditAccount("d0", 200));

        assertEquals(d0, c0);

        Account d10 = new DebitAccount("d1", 200, 0);
        Account c10 = new CreditAccount("c1", 100);

        assertEquals(d1.hashCode(), d10.hashCode());
        assertEquals(d1, d10);

        assertEquals(c1.hashCode(), c10.hashCode());
        assertEquals(c1, c10);
    }

    @Test
    void testToString() {
        d0.setName("RealName");
        assertEquals("RealName", d0.getName());
        assertEquals("RealName", d0.toString());
    }

    @Test
    void testAddSubBalance() {
        assertEquals(0, c0.getBalance());
        c0.addBalance(50);
        c0.subBalance(30);
        assertEquals(20, c0.getBalance());
    }


    @Test
    void testAccountInformation() {
        assertEquals("Account Type: Debit\n"
                        + "Account Name: d1\n"
                        + "Balance: 200.0\n"
                        + "Interest Rate: 0.05\n",
                d1.accountInformation());
        assertEquals("Account Type: Credit\n"
                        + "Account Name: c1\n"
                        + "Balance: 0.0\n"
                        + "Remaining Credit: 100.0",
                c1.accountInformation());
    }

    @Test
    void testSetCustomer() {
        Customer c1 = new Customer("c1", "123");
        Customer c2 = new Customer("c2", "123");

        d0.setCustomer(c1);
        assertEquals(c1, d0.getCustomer());
        assertTrue(c1.getAccounts().contains(d0));

        d0.setCustomer(c2);
        assertEquals(c2, d0.getCustomer());
        assertFalse(c1.getAccounts().contains(d0));
    }
}
