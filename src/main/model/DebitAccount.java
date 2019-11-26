package model;

import java.util.ArrayList;
import java.util.List;

public class DebitAccount extends TransferableAccount implements UsingInterestRate {

    private double interestRate;

    // Constructor
    public DebitAccount(String name) {
        super(name);
        interestRate = 0;
    }

    public DebitAccount(String name, double balance) {
        super(name, balance);
        interestRate = 0;
    }

    public DebitAccount(String name, double balance, double interestRate) {
        super(name, balance);
        this.interestRate = interestRate;
    }

    @Override
    public String getType() {
        return "Debit";
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public double getValue() {
        return getBalance();
    }

    @Override
    public void setInterestRate(double ir) {
        interestRate = ir;
    }

    //EFFECTS: give an overview of the account
    @Override
    public String accountInformation() {
        return "Account Type: " + getType() + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance: " + getBalance() + "\n"
                + "Interest Rate: " + getInterestRate() + "\n";
    }

    //REQUIRES: interest is a number between 0 and 1
    //MODIFIES: this.balance
    //EFFECTS: update the balance to the next interest rate period
    @Override
    public void updateNextPeriod() {
        balance *= (1 + interestRate);
    }

}

