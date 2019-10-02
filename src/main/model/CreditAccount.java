package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CreditAccount implements Account {
    public static final String ACCOUNT_TYPE = "Credit";

    public double balance;
    public String name;
    public static final List<String> options = Collections.unmodifiableList(Arrays.asList(
            "Make Purchase"));

    //EFFECT: create a new CreditAccount
    public CreditAccount() {
        balance = 0;
        name = "Unnamed";
    }

    public CreditAccount(String n, double b) {
        balance = b;
        name = n;
    }

    //MODIFIES: this
    //EFFECT: make a purchase, credit will increase
    public void makePurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the total price of the bill:");
        double bill = Double.parseDouble(scanner.nextLine());
        addBalance(bill);
    }

    //MODIFIES: this
    //EFFECTS: change the name of this.name
    @Override
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: add balance to the account
    @Override
    public void addBalance(double add) {
        this.balance += add;
    }

    //MODIFIES: this
    //EFFECTS: subtract balance from the account
    @Override
    public void subBalance(double sub) {
        this.balance -= sub;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    //EFFECTS: get an overview of the account
    @Override
    public void displayAccount() {
        System.out.println("Account Type: " + ACCOUNT_TYPE);
        System.out.println("Account Name: " + getName());
        System.out.println("Balance:" + getBalance());
    }

    //MODIFIES: this
    //EFFECTS: change the name of the account
    @Override
    public void changeName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the new name for your account:");
        String name = scanner.nextLine();
        this.setName(name);
    }

    //EFFECTS: show the options, quit if chosen, otherwise deal with options
    @Override
    public void accountOperation() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            displayAccount();
            System.out.println("What do you want to do with your account?");
            System.out.println("[1] Make Purchase [Q] Quit");
            String option = scanner.nextLine();

            if (option.equals("Q")) {
                System.out.println("Thank you!");
                break;
            }

            chooseOptions(option);
        }
    }

    //EFFECTS: deal with options
    @Override
    public void chooseOptions(String option) {
        switch (option) {
            case "1": {
                makePurchase();
                break;
            }
            default:
                System.out.println("Invalid Option");
        }
    }

    @Override
    public String saveAccountLine() throws IOException {
        return "Credit " + getName() + " " + getBalance();
    }

}

