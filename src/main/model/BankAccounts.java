package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
        System.out.println("Hello, " + userName + "! Here is your accounts:");
        System.out.println("-------------");
        int i = 0;
        for (Account next : accounts) {
            System.out.println("Account Number [" + ++i + "]");
            next.displayAccount();
            System.out.println("-------------");
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
                saveAccounts();
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
                removeAnAccount();
                break;
            }

            case "3": {
                changeAccount();
                break;
            }
            default:
                System.out.println("Invalid Option");
        }
    }

    //MODIFIES: this.accounts
    //EFFECTS: make changes to an account
    private void changeAccount() {
        accounts.get(findAccountIndex()).accountOperation();
    }

    //MODIFIES: this.accounts
    //EFFECTS: remove an given account
    private void removeAnAccount() {
        removeAccount(accounts.get(findAccountIndex()));
    }


    //MODIFIES: this.accounts
    //EFFECTS: add a new unnamed debit account to the accounts
    public void openAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which type of account do you want to open?");
        System.out.println("[1] Debit [2] Credit");
        String option = scanner.nextLine();

        switch (option) {
            case "1": {
                addAccount(new DebitAccount());
                break;
            }
            case "2": {
                addAccount(new CreditAccount());
                break;
            }
            default:
                System.out.println("Invalid");
        }
    }


    //EFFECTS: transfer the index given by user to the index in the internal system
    private int findAccountIndex() {
        System.out.println("Which account?");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine()) - 1;
    }

    public void loadAccounts() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/BankAccounts.txt"));
        for (String line : lines) {
            ArrayList<String> partsOfLine = splitOnSpace(line);
            String type = partsOfLine.get(0);
            if (type.equals("Credit")) {
                addAccount(new CreditAccount(
                        partsOfLine.get(1), Double.parseDouble(partsOfLine.get(2))));
            } else if (type.equals("Debit")) {
                addAccount(new DebitAccount(
                        partsOfLine.get(1), Double.parseDouble(partsOfLine.get(2)),
                        Double.parseDouble(partsOfLine.get(3))));
            }
        }
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void saveAccounts() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/BankAccounts.txt"));
        PrintWriter writer = new PrintWriter("./data/BankAccounts.txt", "UTF-8");

        for (Account next : accounts) {
            writer.println(next.saveAccountLine());
        }

        writer.close();
    }

}
