package ui;

import model.BankAccount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount();

        while (true) {
            account.checkMyAccount();
            System.out.println("What do you want to do with your account?");
            System.out.println("[1] Change name [2] Deposit [3] Withdraw [4] Quit");
            String option = scanner.nextLine();

            if (option.equals("4")) {
                System.out.println("Thank you!");
                break;
            }

            chooseOptions(account, option);
        }

    }

    private static void chooseOptions(BankAccount account, String option) {
        switch (option) {
            case "1": {
                account.changeName();
                break;
            }
            case "2": {
                account.makeDeposit();
                break;
            }

            case "3": {
                account.withdrawDeposit();
                break;
            }

            default:
                System.out.println("Invalid Option");
        }
    }
}

