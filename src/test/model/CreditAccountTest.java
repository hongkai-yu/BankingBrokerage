package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTest {

    private CreditAccount creditAccount;

    @BeforeEach
    void setUp() {
        creditAccount = new CreditAccount("Credit", 1000);
    }

    @Test
    void testMakePurchaseRequestMoney() {
        assertEquals(1000, creditAccount.getCredit());
        assertEquals(1000,creditAccount.getCreditRemain());

        assertFalse(creditAccount.makePurchase(2000));

        assertTrue(creditAccount.makePurchase(500));
        assertEquals(500, creditAccount.getBalance());
        assertEquals(500,creditAccount.getCreditRemain());

        TransferableAccount payer = new DebitAccount("Payer", 100.0);

        assertFalse(creditAccount.requestPayment(payer,200));

        assertTrue(creditAccount.requestPayment(payer,50));
        assertEquals(450, creditAccount.getBalance());
        assertEquals(550,creditAccount.getCreditRemain());

        creditAccount.setCredit(1100);
        assertEquals(650,creditAccount.getCreditRemain());
    }
}