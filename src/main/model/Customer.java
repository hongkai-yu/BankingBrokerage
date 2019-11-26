package model;

import model.exception.DuplicationException;
import model.exception.NoSuchAccountException;
import model.investment.InvestmentAccount;

import java.io.*;
import java.util.*;


public class Customer implements Serializable, Observer {

    private String userName;
    private String password;
    private Set<Account> accounts;
    private Bank bank;

    // Constructor
    public Customer(String userName, String password) {
        this.userName = userName;
        this.password = password;
        accounts = new HashSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Bank getBank() {
        return bank;
    }

    //EFFECTS: return a set of this's accounts' name
    public Set<String> getAccountsName() {
        Set<String> result = new HashSet<>();
        for (Account account : accounts) {
            result.add(account.getName());
        }
        return result;
    }

    public void setUserName(String name) {
        userName = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    //MODIFIES: this, bank
    //EFFECTS: set this.bank as bank, also include this into the bank's set of customers
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

    //MODIFIES: this, bank
    //EFFECTS: remove this.bank, also remove this from the bank's set of customers
    public void removeBank() {
        if (!(getBank() == null)) {
            Bank saveBank = getBank();
            bank = null;
            saveBank.removeCustomer(this);
        }
    }

    //EFFECTS: return if this contains the given account
    public boolean hasAccount(Account account) {
        return getAccounts().contains(account);
    }

    //EFFECTS: return an account by the given account name, throw an exception if not found
    public Account getAccountByName(String name) throws NoSuchAccountException {
        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        throw new NoSuchAccountException();
    }

    //MODIFIES: this, account
    //EFFECTS: remove account from account, also remove this as account's customer
    public boolean removeAccount(Account account) {
        if (accounts.contains(account)) {
            accounts.remove(account);
            account.removeCustomer();
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: this
    //EFFECTS: remove an account by name, throw an exception if not found
    public boolean removeAccount(String accountName) throws NoSuchAccountException {
        return removeAccount(getAccountByName(accountName));
    }

    //MODIFIES: this, account
    //EFFECTS: set this.account into accounts, also set this as account's customer
    public boolean addAccount(Account account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
            account.setCustomer(this);
            return true;
        } else {
            return false;
        }
    }

    //EFFECTS: Return the number of accounts in the list
    public int numberOfAccounts() {
        return accounts.size();
    }

    //MODIFIES: this.accounts
    //EFFECTS: add an account with given name and type to accounts,
    //          throw an exception if already has an account with the same name
    public void openAccount(String type, String name) throws DuplicationException {
        Account newAccount;
        switch (type) {
            case "Credit": {
                newAccount = new CreditAccount(name);
                break;
            }
            case "Investment": {
                newAccount = new InvestmentAccount(name);
                break;
            }
            case "Debit":
            default: {
                newAccount = new DebitAccount(name);
            }
        }
        if (!addAccount(newAccount)) {
            throw new DuplicationException();
        }
    }

    public boolean checkPassword(String input) {
        return (this.password.equals(input));
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


    //REQUIRES: the arg is a valid double number
    //MODIFIES: this.accounts
    //EFFECTS: set all the interest rate of the accounts as the given interest rate
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
