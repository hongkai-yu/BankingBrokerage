package ui;

import model.Account;
import model.CreditAccount;

import java.util.Scanner;

public class CreditAccountManager extends AccountManager {

    private CreditAccount creditAccount;

    public CreditAccountManager(Account account) {
        super(account);
        creditAccount = (CreditAccount) account;
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
                makePurchase();
                break;
            }
            default:
                System.out.println("Invalid Option");
        }
    }

    //MODIFIES: this
    //EFFECT: make a purchase, credit will increase
    private void makePurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the total amount of the bill: ");
        double bill = Double.parseDouble(scanner.nextLine());
        creditAccount.addBalance(bill);
    }
}
