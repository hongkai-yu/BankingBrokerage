package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.Account;

import java.awt.image.PackedColorModel;

public class TestAccount {

    public Account account;

    @BeforeEach
    private void runBefore() {
        account = new Account();
    }

    @Test
    public void testSetName() {
        assertEquals(account.getName(),"Unnamed");
        account.setName("RealName");
        assertEquals(account.getName(),"RealName");
    }

    @Test
    public void testAddBalance() {
        assertEquals(account.getBalance(),0);
        account.addBalance(50);
        assertEquals(account.getBalance(),50);
    }

    @Test
    public void testSubBalance() {
        assertEquals(account.getBalance(),0);
        account.subBalance(50);
        assertEquals(account.getBalance(),-50);
    }

    @Test
    public void testDisplayAccount() {
        account.displayAccount();
        account.setName("MyName");
        account.addBalance(500);
        account.displayAccount();
    }

    @Test
    // Please enter 50
    public void testMakeDeposit() {
        account.makeDeposit();
        assertEquals(account.getBalance(),50);
    }

    @Test
    public void testWithDrawDeposit() {}

    @Test
    public void testTransferMoney() {
        Account payee = new Account();
        account.transferMoney(payee,50.0);

        account.addBalance(50);
        account.transferMoney(payee,30.0);

        assertEquals(account.getBalance(),20);
        assertEquals(payee.getBalance(),30);

    }
}
