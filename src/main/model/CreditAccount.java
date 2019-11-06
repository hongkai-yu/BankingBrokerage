package model;

import java.util.*;

public class CreditAccount extends Account {

    // Constructor
    public CreditAccount() {
        super();
    }

    public CreditAccount(String name) {
        super(name);
    }

    public CreditAccount(String n, double b) {
        super(n, b);
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

//    @Override
//    public String optionsOfAccount() {
//        return "[1] Change Name [2] Make Purchase ";
//    }

    //EFFECTS: get an overview of the account
    @Override
    public String accountInformation() {
        return "Account Type: " + getType() + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance: " + getBalance() + "\n";
    }

    @Override
    public String saveAccountLine() {
        return "Credit " + getName() + " " + getBalance();
    }
}

