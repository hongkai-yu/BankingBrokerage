package ui;

import model.*;

import java.util.Scanner;

public class DebitAccountUI extends AccountUI {

    public DebitAccountUI(Account account) {
        super(account);
    }

    //MODIFIES: this
    //EFFECTS: withdraw deposit from the account
    private void withdrawDepositUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to withdraw:");
        double withdraw = Double.parseDouble(scanner.nextLine());

        DebitAccount debitAccount = (DebitAccount) account;

        try {
            debitAccount.withdrawDeposit(withdraw);
        } catch (CannotWithdraw e) {
            System.out.println(e.response());
        } finally {
            System.out.println("Tried Withdraw");
        }

//        if (withdraw > account.getBalance()) {
//            System.out.println("Not enough money!");
//        } else {
//            account.subBalance(withdraw);
//            System.out.println("Withdraw succeeded");
//        }
    }

    //REQUIRES: the amount of money > 0
    //MODIFIES: this
    //EFFECTS: make deposit from the account
    private void makeDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the amount of money you want to deposit:");
        double deposit = Double.parseDouble(scanner.nextLine());
        account.addBalance(deposit);
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
                withdrawDepositUI();
                break;
            }
            default:
                System.out.println("Invalid Option");
        }
    }
}
