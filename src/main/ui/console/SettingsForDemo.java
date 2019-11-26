package ui.console;

import model.Bank;
import model.CreditAccount;
import model.Customer;
import model.DebitAccount;
import model.investment.InvestmentAccount;
import model.investment.Stock;

import java.io.IOException;

public class SettingsForDemo {

    public static final String FILE_PATH = "./data/Bank.txt";

    public static Bank constructBank() throws IOException {
        Bank bank;

        bank = new Bank();
        bank.setInterestRate(0.1);

        bank.addCustomer(constructCustomerHongkai());
        bank.addCustomer(constructCustomerTest());

        bank.save(FILE_PATH);
        return bank;
    }

    public static Customer constructCustomerTest() {
        Customer test = new Customer("Test", "123456");

        test.addAccount(new CreditAccount("Test-Credit"));
        test.addAccount(new DebitAccount("Test-Debit"));
        test.addAccount(new InvestmentAccount("Test-Investment"));

        return test;
    }


    public static Customer constructCustomerHongkai() {
        Customer hongkai = new Customer("hongkaiy", "90316894");

        hongkai.addAccount(new CreditAccount("Hongkai-Credit", 100));
        hongkai.addAccount(new DebitAccount("Hongkai-Debit", 1000, 0.1));

        InvestmentAccount investmentAccount = new InvestmentAccount("Hongkai-Investment", 5000);
        investmentAccount.addStock(new Stock("AAPL", 10), 100);
        hongkai.addAccount(investmentAccount);

        return hongkai;
    }


    public static void main(String[] args) throws IOException {
        constructBank();
    }
}

