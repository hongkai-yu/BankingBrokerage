package test;

import model.Account;
import model.CreditAccount;
import model.DebitAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
}
