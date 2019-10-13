package ui;

import model.Account;
import model.CreditAccount;

import java.util.Scanner;

public class CreditAccountUI extends AccountUI {


    public CreditAccountUI(Account account) {
        super(account);
    }

    //MODIFIES: this
    //EFFECT: make a purchase, credit will increase
    public void makePurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the total price of the bill:");
        double bill = Double.parseDouble(scanner.nextLine());
        account.addBalance(bill);
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

}
