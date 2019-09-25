//package model;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class CreditAccount extends Account {
//
//    //EFFECT: create a new CreditAccount
//    public CreditAccount() {
//        balance = 0;
//        name = "Unnamed";
//    }
//
//    //MODIFIES: this
//    //EFFECT: make a purchase, credit will increase
//    public void makePurchase() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter the total price of the bill:");
//        double bill = Double.parseDouble(scanner.nextLine());
//        addBalance(bill);
//    }
//}
