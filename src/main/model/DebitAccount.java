package model;

import model.exception.InsufficientFundException;
import model.exception.NegativeAmountException;

import java.util.ArrayList;
import java.util.List;

public class DebitAccount extends Account implements UsingInterestRate {

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
    public List<String> getOptions() {
        List<String> options = new ArrayList<String>();
        options.add("Change Name");
        options.add("Deposit");
        options.add("Withdraw");
        return options;
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

    @Override
    public String accountInformation() {
        return "Account Type: " + getType() + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance: " + getBalance() + "\n";
    }


    //MODIFIES: this
    //EFFECTS: withdraw money from the debit account
    public void withdrawDeposit(Double amount) throws NegativeAmountException, InsufficientFundException {
        if (amount > getBalance()) {
            throw new InsufficientFundException();
        }
        if (amount < 0) {
            throw new NegativeAmountException();
        }
        subBalance(amount);
    }

//    @Override
//    public String optionsOfAccount() {
//        return "[1] Change Name [2] Deposit [3] Withdraw ";
//    }


    //MODIFIES: this, account
    //EFFECTS: transfer money from this to account, true if succeeded, false otherwise.
    public boolean transferMoney(DebitAccount debitAccount, Double amount) {
        if (amount > balance) {
            return false;
        } else {
            this.subBalance(amount);
            debitAccount.addBalance(amount);
            return true;
        }
    }

    @Override
    public String saveAccountLine() {
        return "Debit " + getName() + " " + getBalance() + " " + getInterestRate();
    }

    @Override
    public void updateNextPeriod() {
        balance *= (1 + interestRate);
    }

}

