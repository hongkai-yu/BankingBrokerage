package model;

import model.exception.DuplicateAccounts;
import model.exception.InvalidOperation;
import model.exception.NoSuchAccountException;

import java.io.*;
import java.util.*;


public class Customer {

    private String userName;
//    private String password;

    private IdentityChecker identityChecker;

    private Map<String, Account> accountsMap;



    // Constructor
    public Customer(String userName, String password) {
        this.userName = userName;
        identityChecker = new IdentityChecker("password");
        setPassword(password);
        accounts = new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    //EFFECTS: set the username of the bank account
    public void setUserName(String name) {
        userName = name;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    //EFFECTS: add an account to this customer
    public boolean addAccount(Account account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
            account.setCustomer(this);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: remove an account to the bank accounts, based on the account
    public boolean removeAccount(Account account) {
        if (accounts.contains(account)) {
            accounts.remove(account);
            account.removeCustomer();
            return true;
        } else {
            return false;
        }
    }

    public boolean removeAccount(String accountName) throws NoSuchAccountException {
        return removeAccount(getAccountByName(accountName));
    }

    //EFFECTS: Return the number of accounts in the list
    public int numberOfAccounts() {
        return accounts.size();
    }

    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        options.add("Open An Account");
        options.add("Remove An Account");
        options.add("Operate An Account");
        options.add("Save");
        return options;
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

    public boolean checkPassword(String input) {
//        return password.equals(input);
        return identityChecker.checkPassword(input);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//        Customer customer = (Customer) o;
//        return userName.equals(customer.userName)
//                && accountsMap.equals(customer.accountsMap);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userName, accountsMap);
//    }

    @Override
    public void update(Observable o, Object arg) {
        double interestRate = (double) arg;
        for (Account account : accounts) {
            if (account.getType().equals("Debit")) {
                DebitAccount debitAccount = (DebitAccount) account;
                debitAccount.setInterestRate(interestRate);
            }
        }
    }
}
