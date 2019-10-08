package ui;

import model.Account;
import model.Account.*;
import sun.lwawt.macosx.CPrinterDevice;
import sun.lwawt.macosx.CPrinterDialog;

import java.util.Scanner;

public abstract class AccountUI {

    Account account;

    public AccountUI(Account account) {
        this.account = account;
    }

    public static void displayAccount(Account account) {
        System.out.printf(account.accountInformation());
    }

    //MODIFIES: this
    //EFFECTS: change the name of the account
    public void changeName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please write the new name for your account:");
        String name = scanner.nextLine();
        account.setName(name);
    }

    //EFFECTS: show the options, quit if chosen, otherwise deal with options
    public void accountOperation() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            displayAccount(account);
            System.out.println("What do you want to do with your account?");
            System.out.printf(account.optionsOfAccount());
            System.out.println(" [Q] Quit");
            String option = scanner.nextLine();

            if (option.equals("Q")) {
                System.out.println("Thank you!");
                break;
            }

            chooseOptions(option);
        }
    }

    //EFFECTS: deal with options
    protected abstract void chooseOptions(String option);
}
