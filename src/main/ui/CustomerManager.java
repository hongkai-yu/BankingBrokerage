package ui;

import model.Account;
import model.Customer;
import model.exception.DuplicateAccounts;
import model.exception.InvalidOperation;

import java.io.IOException;
import java.util.Scanner;

public class CustomerManager {

    private static final String FILE_PATH = "./data/BankAccounts.txt";
    private Customer customer;

    public CustomerManager(Customer customer) {
        this.customer = customer;
    }

    //EFFECTS: change the username of the accounts
    public void changeUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String name = scanner.nextLine();
        customer.setUserName(name);
    }

    //EFFECTS: show the options to user, quit if chosen, otherwise deal with options
    public void bankAccountsOperation() throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            accountOverview();
            System.out.println("[1] Open an account [2] Remove an account [3] Operate an account [S] Save [Q] Quit");
            String option = scanner.nextLine();
            if (option.equals("Q")) {
                System.out.println("Thank you!");
                break;
            } else if (option.equals("S")) {
                customer.saveAccounts(FILE_PATH);
                continue;
            }

            try {
                chooseOptions(option);
            } catch (InvalidOperation e) {
                System.out.println("Invalid Operation.");
            }
        }
    }


    private void chooseOptions(String option) throws InvalidOperation, IOException {
        switch (option) {
            case "1": { // Open a new account
                openAccount();
                break;
            }
            case "2": { // remove an account
                removeAccount();
                break;
            }
            case "3": {
                operateAccount();
                break;
            }
            default:
                throw new InvalidOperation();
        }
        customer.updateName();
    }



    //EFFECTS: Print out the names and balances of the accounts
    private void accountOverview() {
        System.out.println("-------------");
        System.out.println("Hello, " + customer.getUserName() + "! Here are your accounts:");
        for (String next : customer.getAccountsName()) {
            System.out.println(next);
        }
        System.out.println("-------------");
    }

    private void openAccount() throws InvalidOperation {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which type of account do you want to open?");
        System.out.println("[1] Debit [2] Credit");
        String option = scanner.nextLine();
        System.out.print("Please name your new account: ");
        String name = scanner.nextLine();

        try {
            customer.openAccount(option, name);
        } catch (DuplicateAccounts duplicateAccounts) {
            System.out.println("Name already used. Please try another name.");
        }
    }

    private void removeAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the name of the account that you want to remove: ");
        String accountName = scanner.nextLine();
        if (customer.removeAccount(accountName)) {
            System.out.println("Account Removed");
        } else {
            System.out.println("Did not find this account");
        }
    }

    private void operateAccount() throws InvalidOperation {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the name of the account that you want to operate: ");
        String accountName = scanner.nextLine();

        Account account = customer.getAccountsMap().get(accountName);
        if (account == null) {
            throw new InvalidOperation();
        }

        AccountManager accountManager;
        if (account.getType().equals("Debit")) {
            accountManager = new DebitAccountManager(account);
        } else if (account.getType().equals("Credit")) {
            accountManager = new CreditAccountManager(account);
        } else {
            throw new InvalidOperation();
        }

        accountManager.accountOperation();
    }
}
