package model;

import model.exception.DuplicateAccounts;
import model.exception.InvalidOperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Customer {
    private String userName;
    private Map<String, Account> accountsMap;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return userName.equals(customer.userName)
                && accountsMap.equals(customer.accountsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, accountsMap);
    }

    // Constructor
    public Customer() {
        userName = "Unnamed";
        accountsMap = new HashMap<>();
    }

    public String getUserName() {
        return userName;
    }

    public Map<String, Account> getAccountsMap() {
        return accountsMap;
    }

    public Set<String> getAccountsName() {
        return accountsMap.keySet();
    }

    //EFFECTS: set the username of the bank account
    public void setUserName(String name) {
        userName = name;
    }

    //EFFECTS: add an account to this customer
    public boolean addAccount(Account account) {
        if (!accountsMap.containsKey(account.getName())) {
            accountsMap.put(account.getName(), account);
            account.setCustomer(this);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: remove an account to the bank accounts, based on the account
//    public void removeAccount(Account account) {
//        accountsMap.remove(account.getName());
//    }

//    // Overload
//    //EFFECTS: remove an account to the bank accounts, based on index
//    public void removeAccount(int index) {
//        accountsMap.remove(accountsMap.get(index));
//    }

    //EFFECTS: remove an account to the bank accounts, based on the account's name
    public boolean removeAccount(String accountName) {
        if (accountsMap.containsKey(accountName)) {
            accountsMap.remove(accountName);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: remove an account to the bank accounts, based on the account
    public boolean removeAccount(Account account) {
        account.removeCustomer();
        return removeAccount(account.getName());
    }

    //EFFECTS: Return the number of accounts in the list
    public int numberOfAccounts() {
        return accountsMap.size();
    }


    //MODIFIES: this.accounts
    //EFFECTS: add a new unnamed debit account to the accounts
    public void openAccount(String option, String name) throws InvalidOperation, DuplicateAccounts {
        Account newAccount;
        switch (option) {
            case "1": {
                newAccount = new DebitAccount(name);
                break;
            }
            case "2": {
                newAccount = new CreditAccount(name);
                break;
            }
            default:
                throw new InvalidOperation();
        }

        if (!addAccount(newAccount)) {
            throw new DuplicateAccounts();
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
        for (Account next : accountsMap.values()) {
            writer.println(next.saveAccountLine());
        }
        writer.close();
    }

    private static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void updateName() {
        for (String key : getAccountsMap().keySet()) {
            Account account = getAccountsMap().get(key);
            String accountName = account.getName();
            if (!key.equals(accountName)) {
                removeAccount(key);
                getAccountsMap().put(accountName,account);
            }
        }
    }

}
