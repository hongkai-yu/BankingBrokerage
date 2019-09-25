package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankAccounts {
    public String userName;
    public List<Account> accounts;

    // EFFECTS: initially there is no account
    public BankAccounts() {
        userName = "Unnamed";
        accounts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    //EFFECTS: set the username of the bank account
    public void setUserName(String name) {
        userName = name;
    }

    //EFFECTS: add an account to the bank accounts
    public void addAccount(Account account) {
        accounts.add(account);
    }

    //EFFECTS: remove an account to the bank accounts
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    //EFFECTS: Return the number of accounts in the list
    public int numberOfAccounts() {
        return accounts.size();
    }

    //EFFECTS: Print out the names and balances of the accounts
    public void accountOverview() {
        System.out.println("Hello, " + userName + "!");
        System.out.println("Here is your accounts:");
        int i = 0;
        for (Account next: accounts) {
            System.out.println("Account Number [" + ++i + "]");
            next.displayAccount();
        }
    }

    //EFFECTS: change the username of the accounts
    public void changeUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String name = scanner.nextLine();
        setUserName(name);
    }

    //EFFECTS: show the options to user, quit if chosen, otherwise deal with options
    public void bankAccountsOperation() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            accountOverview();
            System.out.println("What do you want to do with your accounts?");
            System.out.println("[1] Open a debit account [2] Remove an account [3] Change a debit account [4] Quit");
            // Need add more functions about credit accounts
            String option = scanner.nextLine();
            if (option.equals("4")) {
                System.out.println("Thank you!");
                break;
            }

            chooseOptions(option);
        }
    }

    //EFFECTS: deal with options
    private void chooseOptions(String option) {
        switch (option) {
            case "1": {
                openDebitAccount();
                break;
            }
            case "2": {
                removeAnAccount();
                break;
            }

            case "3": {
                changeDebitAccount();
                break;
            }
            default:
                System.out.println("Invalid Option");
        }
    }

    //MODIFIES: this.accounts
    //EFFECTS: make changes to an account
    private void changeDebitAccount() {
        accounts.get(findAccountIndex()).debitAccountOperation();
    }

    //MODIFIES: this.accounts
    //EFFECTS: remove an given account
    private void removeAnAccount() {
        removeAccount(accounts.get(findAccountIndex()));
    }


    //MODIFIES: this.accounts
    //EFFECTS: add a new unnamed debit account to the accounts
    public void openDebitAccount() {
        Account account = new Account();
        addAccount(account);
    }


    //EFFECTS: transfer the index given by user to the index in the internal system
    private int findAccountIndex() {
        System.out.println("Which account?");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine()) - 1;
    }
}
