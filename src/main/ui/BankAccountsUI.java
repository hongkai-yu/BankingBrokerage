package ui;

import model.Account;
import model.BankAccounts;

import java.awt.image.PackedColorModel;
import java.io.IOException;
import java.util.Scanner;

public class BankAccountsUI {

    private static final String FILE_PATH = "./data/BankAccounts.txt";
    BankAccounts bankAccounts;

    public BankAccountsUI(BankAccounts bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public void openAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which type of account do you want to open?");
        System.out.println("[1] Debit [2] Credit");
        String option = scanner.nextLine();

        bankAccounts.openAccountOptions(option);
    }

    //EFFECTS: Print out the names and balances of the accounts
    private void accountOverview() {
        System.out.println("Hello, " + bankAccounts.getUserName() + "! Here is your accounts:");
        System.out.println("-------------");
        int i = 0;
        for (Account next : bankAccounts.getAccounts()) {
            System.out.println("Account Number [" + ++i + "]");
            AccountUI.displayAccount(next);
            System.out.println("-------------");
        }
    }

    //EFFECTS: change the username of the accounts
    public void changeUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username:");
        String name = scanner.nextLine();
        bankAccounts.setUserName(name);
    }

    //EFFECTS: show the options to user, quit if chosen, otherwise deal with options
    public void bankAccountsOperation() throws IOException {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            accountOverview();
            System.out.println("What do you want to do with your accounts?");
            System.out.println(
                    "[1] Open an account [2] Remove an account [3] Operate an account [S] Save [Q] Quit");
            String option = scanner.nextLine();
            if (option.equals("Q")) {
                System.out.println("Thank you!");
                break;
            }

            if (option.equals("S")) {
                bankAccounts.saveAccounts(FILE_PATH);
                continue;
            }

            chooseOptions(option);
        }
    }

    //EFFECTS: deal with options
    private void chooseOptions(String option) {
        switch (option) {
            case "1": {
                openAccount();
                break;
            }
            case "2": {
                bankAccounts.removeAccount(findAccountIndex());
                break;
            }
            case "3": {
                Account account = bankAccounts.getAccounts().get(findAccountIndex());
//                accountOperation(account);
                break;
            }
            default:
                System.out.println("Invalid Option");
        }
    }

    //EFFECTS: transfer the index given by user to the index in the internal system
    private int findAccountIndex() {
        System.out.println("Which account?");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine()) - 1;
    }

}
