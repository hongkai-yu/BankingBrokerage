package model;

import java.util.*;

public class CreditAccount extends Account {
    public static final String ACCOUNT_TYPE = "Credit";


    public static final List<String> options = Collections.unmodifiableList(Arrays.asList(
            "Make Purchase"));

    //EFFECT: create a new CreditAccount
    public CreditAccount() {
        super();
    }

    public CreditAccount(String n, double b) {
        super(n, b);
    }


    //EFFECTS: get an overview of the account
    @Override
    public String accountInformation() {
        return "Account Type: " + ACCOUNT_TYPE + "\n"
                + "Account Name: " + getName() + "\n"
                + "Balance:" + getBalance() + "\n";
    }

    @Override
    public String optionsOfAccount() {
        return "[1] Make Purchase";
    }


    @Override
    public String saveAccountLine() {
        return "Credit " + getName() + " " + getBalance();
    }



}

