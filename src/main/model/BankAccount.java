package model;

public class BankAccount {
    public double balance;
    public String name;

    //EFFECT: create a new BankAccount
    public BankAccount() {
        balance = 0;
        name = "Undefined";
    }

    //MODIFIES: this
    //EFFECT: change the name of this.name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECT: add balance to the account
    public void addBalance(double add) {
        this.balance += add;
    }

    //MODIFIES: this
    //EFFECT: withdraw balance from the account
    public void subBalance(double sub) {
        this.balance -= sub;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }
}
