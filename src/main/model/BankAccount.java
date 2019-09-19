package model;

import java.util.Scanner;

public class BankAccount {
    public double balance;
    public String name;

    //EFFECT: create a new BankAccount
    public BankAccount() {
        balance = 0;
        name = "Undefined";
    }

    //MODIFIES: this
    //EFFECT: change the name of this.name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECT: add balance to the account
    public void addBalance(double add) {
        this.balance += add;
    }

    //MODIFIES: this
    //EFFECT: withdraw balance from the account
    public void subBalance(double sub) {
        this.balance -= sub;
    }

    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    public void withdrawDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to withdraw:");
        double withdraw = Double.parseDouble(scanner.nextLine());
        this.subBalance(withdraw);
    }

    public void makeDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to deposit:");
        double deposit = Double.parseDouble(scanner.nextLine());
        this.addBalance(deposit);
    }

    public void changeName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the new name for your account:");
        String name = scanner.nextLine();
        this.setName(name);
    }

    //EFFECT: get an overview of the account
    public void checkMyAccount() {
        System.out.println("This is your account: " + this.getName());
        System.out.println("It's balance is: " + this.getBalance());
    }

}
