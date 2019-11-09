package ui;

import model.*;
import model.exception.NegativeAmountException;

import java.util.Scanner;

public class DebitAccountManager extends AccountManager {

    private DebitAccount debitAccount;

    public DebitAccountManager(Account account) {
        super(account);
        debitAccount = (DebitAccount) account;
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

    //REQUIRES: the amount of money > 0
    //MODIFIES: this
    //EFFECTS: make deposit from the account
    private void makeDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to deposit:");
        double deposit = Double.parseDouble(scanner.nextLine());
        debitAccount.addBalance(deposit);
    }

    //MODIFIES: this
    //EFFECTS: withdraw deposit from the account
    private void withdrawDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to withdraw:");
        double withdraw = Double.parseDouble(scanner.nextLine());

        try {
            if (debitAccount.withdrawDeposit(withdraw)) {
                System.out.println("Withdraw Successful");
            } else {
                System.out.println("Not enough Fund");
            }
        } catch (NegativeAmountException e) {
            System.out.println(e.response());
        } finally {
            System.out.println("Tried Withdraw");
        }
    }
}
