package ui;

import model.Customer;

import java.io.IOException;

public class BankDemo {
    public static final String FILE_PATH = "./data/BankAccounts.txt";

    // We only can only deal with one customer now
    public BankDemo() throws IOException {
        Customer customer = new Customer();
        customer.loadAccounts(FILE_PATH);
        CustomerManager customerManager = new CustomerManager(customer);
        customerManager.changeUserName();
        customerManager.bankAccountsOperation();
    }

    public static void main(String[] args) throws IOException {
        new BankDemo();
    }
}

