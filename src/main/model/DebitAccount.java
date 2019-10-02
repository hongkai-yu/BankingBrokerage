package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DebitAccount implements Account, UsingInterestRate {

    public static final String ACCOUNT_TYPE = "Debit";

    private double balance;
    private String name;
    double interestRate;

//    public static final List<String> options = Collections.unmodifiableList(Arrays.asList(
//            "Change Name", "Deposit", "Withdraw"));

    //EFFECTS: create a new BankAccount
    public DebitAccount() {
        balance = 0;
        name = "Unnamed";
        interestRate = 0;
    }

    public DebitAccount(String n, double b, double ir) {
        balance = b;
        name = n;
        interestRate = ir;
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

    //MODIFIES: this
    //EFFECTS: withdraw deposit from the account
    public void withdrawDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to withdraw:");
        double withdraw = Double.parseDouble(scanner.nextLine());
        if (withdraw > balance) {
            System.out.println("Not enough money!");
        } else {
            this.subBalance(withdraw);
            System.out.println("Withdraw succeeded");
        }
    }

    //REQUIRES: the amount of money > 0
    //MODIFIES: this
    //EFFECTS: make deposit from the account
    public void makeDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to deposit:");
        double deposit = Double.parseDouble(scanner.nextLine());
        this.addBalance(deposit);
    }


    //MODIFIES: this, account
    //EFFECTS: transfer money from this to account
    public void transferMoney(DebitAccount debitAccount, Double amount) {
        if (amount > balance) {
            System.out.println("Not enough money");
        } else {
            this.subBalance(amount);
            debitAccount.addBalance(amount);
            System.out.printf("Transfer Succeeded");
        }
    }

    //EFFECTS: show the options, quit if chosen, otherwise deal with options
    @Override
    public void accountOperation() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            displayAccount();
            System.out.println("What do you want to do with your account?");
            System.out.println("[1] Change name [2] Deposit [3] Withdraw [Q] Quit");
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
                changeName();
                break;
            }
            case "2": {
                makeDeposit();
                break;
            }

            case "3": {
                withdrawDeposit();
                break;
            }
            default:
                System.out.println("Invalid Option");
        }
    }

    @Override
    public String saveAccountLine() throws IOException {
        return  "Debit " + getName() + " " + getBalance() + " " + getInterestRate();
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double ir) {
        interestRate = ir;

    }

    @Override
    public void updateNextPeriod() {
        balance *= (1 + interestRate);
    }

}

