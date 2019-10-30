package model;

import model.exception.CannotWithdraw;
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

        assertEquals(20,debitAccount.getValue());
        assertEquals(30,payee.getValue());

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
            assertEquals("Negative amount!",e.response());
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
            assertEquals("Not enough fund!",e.response());
        }

    }
}
