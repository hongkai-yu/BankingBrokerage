package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.DebitAccount;

public class DebitAccountTest {

    public DebitAccount debitAccount;

    @BeforeEach
    private void runBefore() {
        debitAccount = new DebitAccount();
    }

    @Test
    public void testSetName() {
        assertEquals(debitAccount.getName(),"Unnamed");
        debitAccount.setName("RealName");
        assertEquals(debitAccount.getName(),"RealName");
    }

    @Test
    public void testAddBalance() {
        assertEquals(debitAccount.getBalance(),0);
        debitAccount.addBalance(50);
        assertEquals(debitAccount.getBalance(),50);
    }

    @Test
    public void testSubBalance() {
        assertEquals(debitAccount.getBalance(),0);
        debitAccount.subBalance(50);
        assertEquals(debitAccount.getBalance(),-50);
    }

    @Test
    public void testTransferMoney() {
        DebitAccount payee = new DebitAccount();
        debitAccount.transferMoney(payee,50.0);

        debitAccount.addBalance(50);
        debitAccount.transferMoney(payee,30.0);

        assertEquals(debitAccount.getBalance(),20);
        assertEquals(payee.getBalance(),30);

    }
}
