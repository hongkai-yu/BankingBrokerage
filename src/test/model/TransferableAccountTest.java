package model;

import model.exception.NegativeAmountException;
import model.investment.InvestmentAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferableAccountTest {

    private TransferableAccount transferableAccount;

    @BeforeEach
    void runBefore() {
        transferableAccount = new DebitAccount("debit");
    }

    @Test
    void testTransferMoney() {
        Account payee = new InvestmentAccount("investment");
        transferableAccount.addBalance(10);
        assertFalse(transferableAccount.transferMoney(payee, 50.0));

        transferableAccount.addBalance(50);
        assertTrue( transferableAccount.transferMoney(payee, 30.0));

        assertEquals(30, transferableAccount.getBalance());
        assertEquals(30, payee.getBalance());

        CreditAccount payeeCreditCard = new CreditAccount("Credit",1000);
        assertTrue(payeeCreditCard.makePurchase(10));
        assertTrue(transferableAccount.transferMoney(payeeCreditCard, 20.0));
        assertEquals(10,transferableAccount.getBalance());
        assertEquals(-10,payeeCreditCard.getBalance());
    }

    @Test
    void testWithdrawDeposit() {
        double a1 = -100;
        double a2 = 10;
        double a3 = 100000;

        transferableAccount.setBalance(100);
        try {
            transferableAccount.withdrawDeposit(a1);
            fail();
        } catch (NegativeAmountException e) {
            assertEquals("Negative amount!", e.response());
        }

        try {
            assertTrue(transferableAccount.withdrawDeposit(a2));
        } catch (NegativeAmountException e) {
            fail();
        }

        transferableAccount.setBalance(100);
        try {
            assertFalse(transferableAccount.withdrawDeposit(a3));
        } catch (NegativeAmountException e) {
            fail();
        }

    }

}
