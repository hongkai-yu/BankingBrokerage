package model;

import java.util.*;

public class CreditAccount extends Account {

    private double credit;

    // Constructor
    public CreditAccount(String name) {
        super(name);
    }

    public CreditAccount(String name, double credit) {
        super(name);
        this.credit = credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCredit() {
        return credit;
    }

    @Override
    public String getType() {
        return "Credit";
    }

    public double getCreditRemain() {
        return credit - balance;
    }

    //REQUIRES: the amount is not negative
    //MODIFIES: this
    //EFFECTS: increase balance by the given amount and return true if has sufficient credit, return false otherwise
    public boolean makePurchase(double amount) {
        if (balance + amount > credit) {
            return false;
        } else {
            balance += amount;
            return true;
        }
    }

    //MODIFIES: payer, this
    //EFFECTS: require the payer to pay for the bill
    public boolean requestPayment(TransferableAccount payer, double amount) {
        return payer.transferMoney(this, amount);
    }

    //EFFECTS: get an overview of the account
    @Override
    public String accountInformation() {
        return "Account Type: " + getType() + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance: " + getBalance() + "\n"
                + "Remaining Credit: " + getCreditRemain();
    }

}

