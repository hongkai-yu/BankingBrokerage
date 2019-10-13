package model;

public abstract class Account {


    protected static String ACCOUNT_TYPE;
    protected double balance;
    protected String name;

    public Account() {
        balance = 0;
        name = "Unnamed";
    }

    // Overload
    public Account(String n, double b) {
        balance = b;
        name = n;
    }

    //MODIFIES: this
    //EFFECTS: change the name of this.name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: change the name of this.balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    //MODIFIES: this
    //EFFECTS: add balance to the account
    public void addBalance(double add) {
        this.balance += add;
    }

    //MODIFIES: this
    //EFFECTS: subtract balance from the account
    public void subBalance(double sub) {
        this.balance -= sub;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    //EFFECT: output the account information to a file
    public abstract String saveAccountLine();

    //EFFECTS: get an overview of the account
    public abstract String accountInformation();

    public abstract String optionsOfAccount();


}
