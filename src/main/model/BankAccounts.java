package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BankAccounts {
    private String userName;
    private List<Account> accounts;
//    public static final String FILE_PATH = "./data/BankAccounts.txt";

    // EFFECTS: initially there is no account
    public BankAccounts() {
        userName = "Unnamed";
        accounts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    //EFFECTS: set the username of the bank account
    public void setUserName(String name) {
        userName = name;
    }

    //EFFECTS: add an account to the bank accounts
    public void addAccount(Account account) {
        accounts.add(account);
    }

    //EFFECTS: remove an account to the bank accounts, based on the account
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    // Overload
    //EFFECTS: remove an account to the bank accounts, based on index
    public void removeAccount(int index) {
        accounts.remove(accounts.get(index));
    }


    //EFFECTS: Return the number of accounts in the list
    public int numberOfAccounts() {
        return accounts.size();
    }


    //MODIFIES: this.accounts
    //EFFECTS: add a new unnamed debit account to the accounts
    public void openAccountOptions(String option) {
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


    public void loadAccounts(String path) throws IOException {
//        List<String> lines = Files.readAllLines(Paths.get("./data/BankAccounts.txt"));
        List<String> lines = Files.readAllLines(Paths.get(path));
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

    public void saveAccounts(String path) throws IOException {
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        for (Account next : accounts) {
            writer.println(next.saveAccountLine());
        }

        writer.close();
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
