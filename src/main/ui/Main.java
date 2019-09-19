package ui;

import model.BankAccount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount();
        operationAccount(scanner, account);

    }

    private static void operationAccount(Scanner scanner, BankAccount account) {
        while (true) {
            checkMyAccount(account);
            System.out.println("What do you want to do with your account?");
            System.out.println("[1] Change name [2] Deposit [3] Withdraw [4] Quit");
            String option = scanner.nextLine();

            if (option.equals("4")) {
                System.out.println("Thank you!");
                break;
            }

            chooseOptions(scanner, account, option);
        }
    }

    private static void chooseOptions(Scanner scanner, BankAccount account, String option) {
        switch (option) {
            case "1": {
                changeName(scanner, account);
                break;
            }
            case "2": {
                makeDeposit(scanner, account);
                break;
            }

            case "3": {
                withdrawDeposit(scanner, account);
                break;
            }

            default:
                System.out.println("Invalid Option");
        }
    }

    private static void withdrawDeposit(Scanner scanner, BankAccount account) {
        System.out.println("Please enter the amount of money you want to withdraw:");
        double withdraw = Double.parseDouble(scanner.nextLine());
        account.subBalance(withdraw);
    }

    private static void makeDeposit(Scanner scanner, BankAccount account) {
        System.out.println("Please enter the amount of money you want to deposit:");
        double deposit = Double.parseDouble(scanner.nextLine());
        account.addBalance(deposit);
    }

    private static void changeName(Scanner scanner, BankAccount account) {
        System.out.println("Please write the new name for your account:");
        String name = scanner.nextLine();
        account.setName(name);
    }

    //EFFECT: get an overview of the account
    public static void checkMyAccount(BankAccount account) {
        System.out.println("This is your account: " + account.getName());
        System.out.println("It's balance is: " + account.getBalance());
    }

    /*
    public static void seeIfBankrupt(){
        System.out.println("Oh! You are bankrupt!");
    }

     */
}
