package model;

import model.exception.DuplicateAccounts;
import model.exception.InvalidOperation;
import model.exception.NoSuchAccountException;

import java.io.*;
import java.util.*;


public class Customer implements Serializable, Observer {

    private String userName;
    private IdentityChecker identityChecker;
    private Set<Account> accounts;
    private Bank bank;

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

    public Bank getBank() {
        return bank;
    }

    //EFFECTS: set the username of the bank account
    public void setUserName(String name) {
        userName = name;
    }

    public void setPassword(String password) {
        identityChecker.setPassword(password);
    }

    public void setBank(Bank bank) {
        if (getBank() == null) {
            this.bank = bank;
            getBank().addCustomer(this);
        } else if (!getBank().equals(bank)) {
            getBank().removeCustomer(this);
            this.bank = bank;
            getBank().addCustomer(this);
        }
    }

    public boolean hasAccount(Account account) {
        return getAccounts().contains(account);
    }

    public Account getAccountByName(String name) throws NoSuchAccountException {
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        throw new NoSuchAccountException();
    }

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

    public void removeBank() {
        if (!(getBank() == null)) {
            Bank saveBank = getBank();
            bank = null;
            saveBank.removeCustomer(this);
        }
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

    public boolean checkPassword(String input) {
        return identityChecker.checkPassword(input);
    }

    // Only compare UserName;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return userName.equals(customer.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

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
