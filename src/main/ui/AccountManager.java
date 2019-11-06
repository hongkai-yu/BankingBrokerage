package ui;

import model.Account;

import java.util.Scanner;

public abstract class AccountManager {

    protected Account account;

    protected AccountManager(Account account) {
        this.account = account;
    }

    //EFFECTS: show the options, quit if chosen, otherwise deal with options
    protected void accountOperation() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            displayAccount();
            System.out.println("What do you want to do with your account?");
            System.out.print(account.optionsOfAccount());
            System.out.println("[Q] Quit");
            String option = scanner.nextLine();

            if (option.equals("Q")) {
                System.out.println("Thank you!");
                break;
            }

            chooseOptions(option);
        }
    }

    protected void displayAccount() {
        System.out.print(account.accountInformation());
    }

    //EFFECTS: deal with options
    protected abstract void chooseOptions(String option);

    //MODIFIES: this
    //EFFECTS: change the name of the account
    protected void changeName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the new name for your account:");
        String name = scanner.nextLine();
        account.setName(name);
    }
}
