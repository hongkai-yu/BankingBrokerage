//package model;
//
//import java.util.Scanner;
//
//public class DebitAccount extends Account {
//
//    //EFFECT: create a new CreditAccount
//    public DebitAccount() {
//        balance = 0;
//        name = "Unnamed";
//    }
//
//    public void withdrawDeposit() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter the amount of money you want to withdraw:");
//        double withdraw = Double.parseDouble(scanner.nextLine());
//        this.subBalance(withdraw);
//    }
//
//    public void makeDeposit() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Please enter the amount of money you want to deposit:");
//        double deposit = Double.parseDouble(scanner.nextLine());
//        this.addBalance(deposit);
//    }
//
//    public void debitAccountOperation() {
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//            displayAccount();
//            System.out.println("What do you want to do with your account?");
//            System.out.println("[1] Change name [2] Deposit [3] Withdraw [4] Quit");
//            String option = scanner.nextLine();
//
//            if (option.equals("4")) {
//                System.out.println("Thank you!");
//                break;
//            }
//
//            chooseOptions(option);
//        }
//    }
//
//    private void chooseOptions(String option) {
//        switch (option) {
//            case "1": {
//                changeName();
//                break;
//            }
//            case "2": {
//                makeDeposit();
//                break;
//            }
//
//            case "3": {
//                withdrawDeposit();
//                break;
//            }
//            default:
//                System.out.println("Invalid Option");
//        }
//    }
//
//}
