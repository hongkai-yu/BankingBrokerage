package model;

import java.util.List;
import java.util.Objects;

public abstract class Account {

    protected double balance;
    protected String name;
    protected Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0
                && name.equals(account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, name);
    }

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
        if (o == null || getClass() != o.getClass()) {
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

    public abstract List<String> getOptions();
//        List<String> options = new ArrayList<String>();
//        options.add("Change Name");
//        return options;
//    }

    public abstract String getType();

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCustomer(Customer customer) {
        if (getCustomer() == null) {
            this.customer = customer;
            getCustomer().addAccount(this);
        } else if (!getCustomer().equals(customer)) {
            getCustomer().removeAccount(getName());
            this.customer = customer;
            getCustomer().addAccount(this);
        }
    }

    public void removeCustomer() {
        if (!(getCustomer() == null)) {
            Customer saveCustomer = getCustomer();
            this.customer = null;
            saveCustomer.removeAccount(this);
        }
    }

    public void addBalance(double add) {
        this.balance += add;
    }

    public void subBalance(double sub) {
        this.balance -= sub;
    }

    //EFFECT: output the account information to a file
    public abstract String saveAccountLine();

    //EFFECT: return the String of options available
    public String optionsOfAccount() {
        return OptionsGenerator.generateOptions(getOptions());
//        String result = "";
//        for (int i = 0; i < getOptions().size(); i++) {
//            int index = i + 1;
//            result += "[" + index + "] " + getOptions().get(i) + " ";
//        }
//        return result;
    }

    //EFFECTS: get an overview of the account
    public abstract String accountInformation();

//    @Override
//    public String toString() {
//        return "Account{"
//                + "balance=" + balance
//                + ", name='" + name + '\''
//                + ", customer=" + customer
//                + '}';
//    }
}
