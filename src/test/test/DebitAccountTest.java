package test;

import model.DebitAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
