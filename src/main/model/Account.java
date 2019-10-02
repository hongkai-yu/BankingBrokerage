package model;

import java.io.IOException;

public interface Account {

    //MODIFIES: this
    //EFFECTS: change the name of this.name
    void setName(String name);

    //MODIFIES: this
    //EFFECTS: add balance to the account
    void addBalance(double add);

    //MODIFIES: this
    //EFFECTS: subtract balance from the account
    void subBalance(double sub);

    String getName();

    double getBalance();

    //EFFECTS: get an overview of the account
    void displayAccount();

    //MODIFIES: this
    //EFFECTS: change the name of the account
    void changeName();

    //EFFECTS: show the options, quit if chosen, otherwise deal with options
    void accountOperation();

    //EFFECTS: deal with options
    void chooseOptions(String option);

    //EFFECT: output the account information to a file
    String saveAccountLine() throws IOException;

}
