package model;

import java.util.Scanner;

public class Account {
    private double balance;
    private String name;

    //EFFECTS: create a new BankAccount
    public Account() {
        balance = 0;
        name = "Unnamed";
    }

    //MODIFIES: this
    //EFFECTS: change the name of this.name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: add balance to the account
    public void addBalance(double add) {
        this.balance += add;
    }

    //MODIFIES: this
    //EFFECTS: subtract balance from the account
    public void subBalance(double sub) {
        this.balance -= sub;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    //EFFECTS: get an overview of the account
    public void displayAccount() {
        System.out.println("Account Name: " + this.getName());
        System.out.println("Balance:" + this.getBalance());
    }

    //MODIFIES: this
    //EFFECTS: change the name of the account
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
    public void transferMoney(Account account, Double amount) {
        if (amount > balance) {
            System.out.println("Not enough money");
        } else {
            this.subBalance(amount);
            account.addBalance(amount);
            System.out.printf("Transfer Succeeded");
        }
    }

    //EFFECTS: show the options, quit if chosen, otherwise deal with options
    public void debitAccountOperation() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            displayAccount();
            System.out.println("What do you want to do with your account?");
            System.out.println("[1] Change name [2] Deposit [3] Withdraw [4] Quit");
            String option = scanner.nextLine();

            if (option.equals("4")) {
                System.out.println("Thank you!");
                break;
            }

            chooseOptions(option);
        }
    }

    //EFFECTS: deal with options
    private void chooseOptions(String option) {
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

}

