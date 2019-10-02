package ui;

import model.BankAccounts;

import java.io.IOException;

public class BankDemo {
    public static void main(String[] args) throws IOException {
        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.loadAccounts();
        bankAccounts.changeUserName();
        bankAccounts.bankAccountsOperation();
    }
}

