package ui;

import model.BankAccounts;

public class BankDemo {
    public static void main(String[] args) {
        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.changeUserName();
        bankAccounts.bankAccountsOperation();
    }
}

