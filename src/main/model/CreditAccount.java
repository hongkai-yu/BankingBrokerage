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

    @Override
    public List<String> getOptions() {
        List<String> options = new ArrayList<String>();
        options.add("Change Name");
        options.add("Make Purchase");
        return options;
    }

    public double getCreditRemain() {
        return credit - balance;
    }

    public boolean makePurchase(double amount) {
        if (balance + amount > credit) {
            return false;
        } else {
            balance += amount;
            return true;
        }
    }

    public boolean requestPayment(TransferableAccount payer, double amount) {
        return payer.transferMoney(this,amount);
    }

    //EFFECTS: get an overview of the account
    @Override
    public String accountInformation() {
        return "Account Type: " + getType() + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance: " + getBalance() + "\n";
    }

}

