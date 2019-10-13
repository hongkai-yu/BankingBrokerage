package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DebitAccountTest {

    public DebitAccount debitAccount;

    @BeforeEach
    public void runBefore() {
        debitAccount = new DebitAccount();
    }

    @Test
    public void testTransferMoney() {
        DebitAccount payee = new DebitAccount();
        debitAccount.transferMoney(payee,50.0);

        debitAccount.addBalance(50);
        debitAccount.transferMoney(payee,30.0);

        assertEquals(debitAccount.getValue(),20);
        assertEquals(payee.getValue(),30);

    }

    @Test
    public void testWithdrawDeposit() {
        double a1 = -100;
        double a2 = 10;
        double a3 = 100000;

        debitAccount.setBalance(100);
        try {
            debitAccount.withdrawDeposit(a1);
            fail();
        } catch (CannotWithdraw e) {
            assertEquals(e.response(), "Negative amount!");
        }

        debitAccount.setBalance(100);
        try {
            debitAccount.withdrawDeposit(a2);
        } catch (CannotWithdraw e) {
            fail();
        }

        debitAccount.setBalance(100);
        try {
            debitAccount.withdrawDeposit(a3);
            fail();
        } catch (CannotWithdraw e) {
            assertEquals(e.response(), "Not enough fund!");
        }

    }
}
