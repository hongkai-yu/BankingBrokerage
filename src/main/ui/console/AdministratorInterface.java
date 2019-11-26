package ui.console;

import model.Bank;
import model.Customer;

import java.io.IOException;
import java.util.Scanner;

public class AdministratorInterface {

    private static final String FILE_PATH = "./data/Bank.txt";

    private Bank bank;

    public AdministratorInterface() {

        initializeBank();

        System.out.println("Hello administrator!");
        System.out.println("Currently, the interest rate is " + bank.getInterestRate());

        System.out.println("we have " + bank.countObservers() + " Observers");

        System.out.print("Please enter the new interest rate: ");
        Scanner scanner = new Scanner(System.in);
        double interestRate = Double.parseDouble(scanner.nextLine());

        bank.setInterestRate(interestRate);
        System.out.print("Interest rate changed successfully.");
        try {
            bank.save(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Save Failed");
        }
    }

    private void initializeBank() {
        try {
            bank = Bank.load(FILE_PATH);
            for (Customer customer : bank.getCustomers()) {
                bank.addObserver(customer);
            }
        } catch (Exception e) {
            System.out.println("Broken bank file");
        }
    }

    public static void main(String[] args) {
        new AdministratorInterface();
    }
}
