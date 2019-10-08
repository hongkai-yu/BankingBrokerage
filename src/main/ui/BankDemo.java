package ui;

import model.BankAccounts;

import java.io.IOException;

public class BankDemo {
    public static final String FILE_PATH = "./data/BankAccounts.txt";

    public BankDemo() throws IOException {
        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.loadAccounts(FILE_PATH);
        BankAccountsUI bankAccountsUI = new BankAccountsUI(bankAccounts);
        bankAccountsUI.changeUserName();
        bankAccountsUI.bankAccountsOperation();
    }

    public static void main(String[] args) throws IOException {
        new BankDemo();
    }
}

