package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Account implements Serializable {

    protected double balance;
    protected String name;
    protected Customer customer;

    private List<String> boo;

    // Constructors
    public Account(String name) {
        this.name = name;
        balance = 0;
    }

    // Overload
    public Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    // only consider the name;
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Account account = (Account) o;
        return name.equals(account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public abstract String getType();

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //MODIFIES: this, customer
    //EFFECTS: set this.customer as customer, also include this into the customer's list of accounts
    public void setCustomer(Customer customer) {
        if (getCustomer() == null) {
            this.customer = customer;
            getCustomer().addAccount(this);
        } else if (!getCustomer().equals(customer)) {
            getCustomer().removeAccount(this);
            this.customer = customer;
            getCustomer().addAccount(this);
        }
    }

    //MODIFIES: this, customer
    //EFFECTS: remove this.customer, also remove this from the customer's list of accounts
    public void removeCustomer() {
        if (!(getCustomer() == null)) {
            Customer saveCustomer = getCustomer();
            this.customer = null;
            saveCustomer.removeAccount(this);
        }
    }

    //MODIFIES: this
    //EFFECTS: add balance
    public void addBalance(double add) {
        this.balance += add;
    }

    //MODIFIES: this
    //EFFECTS: sub balance
    public void subBalance(double sub) {
        this.balance -= sub;
    }

    //EFFECTS: get an overview of the account
    public abstract String accountInformation();

    //EFFECTS: just the name of the account, used for the JList GUI
    @Override
    public String toString() {
        return getName();
    }
}
