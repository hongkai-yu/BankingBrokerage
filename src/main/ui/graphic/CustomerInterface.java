package ui.graphic;

import model.Account;
import model.Bank;
import model.Customer;
import model.DebitAccount;
import model.exception.DuplicationException;
import model.exception.NegativeAmountException;
import model.investment.InvestmentAccount;
import model.investment.NoSuchStockOnInternetException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class CustomerInterface extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private static final String FILE_PATH = "./data/Bank.txt";

    private Bank bank;
    private Customer customer;
    private Account activeAccount;

    private JList<Account> accountList;
    private JPanel accountPanel;
    private JTextArea displayInfo;
    private JPanel controlPanel;

    public CustomerInterface(Customer customer, Bank bank) {
        super("BankSystem: " + customer.getUserName());
        this.customer = customer;
        this.bank = bank;

        initializeWindow();
        initializeAccountPanel();
        initializeDisplay();
        initializeControlPanel();

        setVisible(true);
    }


    private void initializeWindow() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initializeAccountPanel() {
        accountPanel = new JPanel(new BorderLayout());
        add(accountPanel, BorderLayout.WEST);

        initializeAccountList();
        accountPanel.add(accountList, BorderLayout.NORTH);

        initializeCustomerButtons();
    }

    private void initializeCustomerButtons() {
        JPanel customerButtons = new JPanel(new GridLayout(4, 1, 1, 1));

        JButton addAccount = new JButton("Open a new account");
        addAccount.addActionListener(new OpenAccountListener());

        JButton removeAccount = new JButton("Remove the account");
        removeAccount.addActionListener(new RemoveAccountListener());

        JButton changePassword = new JButton("Change Password");
        changePassword.addActionListener(new ChangePasswordListener());

        JButton save = new JButton("Save");
        save.addActionListener(new SaveListener());


        customerButtons.add(addAccount);
        customerButtons.add(removeAccount);
        customerButtons.add(changePassword);
        customerButtons.add(save);

        accountPanel.add(customerButtons, BorderLayout.SOUTH);
    }

    private class OpenAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<String> data = InputPanels.openAccountPanel();
                if (InputValidChecker.inputNotNullChecker(data.get(1))) {
                    customer.openAccount(data.get(0), data.get(1));
                    bank.setInterestRate(bank.getInterestRate());
                }
            } catch (CancelException ex) {
                // Do nothing
            } catch (DuplicationException ex) {
                JOptionPane.showMessageDialog(null, "Account name taken");
            }
            CustomerInterface.this.refreshAccountList();
        }
    }

    private class RemoveAccountListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            activeAccount.removeCustomer();
            CustomerInterface.this.refreshAccountList();
        }
    }

    private class ChangePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Please enter the new password:");
            if (InputValidChecker.inputNotNullChecker(input)) {
                customer.setPassword(input);
                JOptionPane.showMessageDialog(null, "Password successfully reset");
            }
        }
    }

    private void refreshAccountList() {
        DefaultListModel<Account> model = new DefaultListModel<>();
        for (Account account : customer.getAccounts()) {
            model.addElement(account);
        }
        accountList.setModel(model);
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                bank.save(FILE_PATH);
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null, "Save Failed");
            }
        }
    }

    private void initializeAccountList() {
        accountList = new JList<>();
        refreshAccountList();
        accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountList.addListSelectionListener(new AccountSectionListener());
    }

    private void initializeDisplay() {
        displayInfo = new JTextArea("Please select an account to display information");
        displayInfo.setEditable(false);
        add(displayInfo, BorderLayout.CENTER);
    }

    private void initializeControlPanel() {
        controlPanel = new JPanel(new BorderLayout());
        add(controlPanel, BorderLayout.EAST);

        JLabel header = new JLabel("Available options:");
        controlPanel.add(header, BorderLayout.NORTH);
    }

    private void findDebitButtons() {
        JPanel accountButtons = new JPanel(new GridLayout(4, 1, 1, 1));
        controlPanel.add(accountButtons, BorderLayout.CENTER);

        JButton changeName = new JButton("Change account name");
        changeName.addActionListener(new ChangeNameListener());
        accountButtons.add(changeName);

        JButton deposit = new JButton("Deposit");
        deposit.addActionListener(new DepositListener());
        accountButtons.add(deposit);

        JButton withdraw = new JButton("Withdraw");
        withdraw.addActionListener(new WithdrawListener());
        accountButtons.add(withdraw);

        JButton nextPeriod = new JButton("Next period");
        nextPeriod.addActionListener(new NextPeriodListener());
        accountButtons.add(nextPeriod);
    }

    private class ChangeNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Please enter the new name for the account:");
            if (!input.equals(activeAccount.getName())) {
                if (!customer.getAccountsName().contains(input)) {
                    activeAccount.setName(input);
                    JOptionPane.showMessageDialog(null, "Name changed successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Name exists");
                }
            } else {
                JOptionPane.showMessageDialog(null, "It's the same name!");
            }
            CustomerInterface.this.refreshAccountList();
        }
    }

    private class DepositListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Please enter the amount of money you want to deposit:");
            if (InputValidChecker.inputNotNullChecker(input)) {
                double deposit = Double.parseDouble(input);
                activeAccount.addBalance(deposit);
                JOptionPane.showMessageDialog(null, "Deposit successfully");
            }
            CustomerInterface.this.refreshDisplay();
        }
    }

    private class WithdrawListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Please enter the amount of money you want to withdraw:");
            if (InputValidChecker.inputNotNullChecker(input)) {
                double withdraw = Double.parseDouble(input);
                DebitAccount debitAccount = (DebitAccount) activeAccount;
                try {
                    if (debitAccount.withdrawDeposit(withdraw)) {
                        JOptionPane.showMessageDialog(null, "Withdrew successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Fund");
                    }
                } catch (NegativeAmountException ex) {
                    JOptionPane.showMessageDialog(null, ex.response());
                }
            }
            CustomerInterface.this.refreshDisplay();
        }
    }

    private class NextPeriodListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DebitAccount debitAccount = (DebitAccount) activeAccount;
            debitAccount.updateNextPeriod();
            CustomerInterface.this.refreshDisplay();
        }
    }

    private void findCreditButtons() {
        JPanel accountButtons = new JPanel(new GridLayout(2, 1, 1, 1));
        controlPanel.add(accountButtons, BorderLayout.CENTER);

        JButton changeName = new JButton("Change account name");
        changeName.addActionListener(new ChangeNameListener());
        accountButtons.add(changeName);

        JButton makePurchase = new JButton("Make purchase");
        makePurchase.addActionListener(new MakePurchaseListener());
        accountButtons.add(makePurchase);
    }

    private class MakePurchaseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = JOptionPane.showInputDialog("Please enter the total amount of the bill:");
            if (InputValidChecker.inputNotNullChecker(input)) {
                double bill = Double.parseDouble(input);
                activeAccount.addBalance(bill);
                JOptionPane.showMessageDialog(null, "Purchased successfully");
            }
            CustomerInterface.this.refreshDisplay();
        }
    }

    private void findInvestmentButtons() {
        JPanel accountButtons = new JPanel(new GridLayout(5, 1, 1, 1));
        controlPanel.add(accountButtons, BorderLayout.CENTER);

        JButton deposit = new JButton("Deposit");
        deposit.addActionListener(new DepositListener());
        accountButtons.add(deposit);

        JButton buyStock = new JButton("Buy stock");
        buyStock.addActionListener(new BuyStockListener());
        accountButtons.add(buyStock);

        JButton sellStock = new JButton("Sell stock");
        sellStock.addActionListener(new SellStockListener());
        accountButtons.add(sellStock);

        JButton sellAllStocks = new JButton("Sell all stocks");
        sellAllStocks.addActionListener(new SellAllStocksListener());
        accountButtons.add(sellAllStocks);

        JButton refreshPrice = new JButton("Refresh stock prices");
        refreshPrice.addActionListener(new RefreshPriceListener());
        accountButtons.add(refreshPrice);
    }

    private class RefreshPriceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InvestmentAccount investmentAccount = (InvestmentAccount) activeAccount;
            try {
                investmentAccount.updateStocksPrice();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No internet connection");
            } catch (NoSuchStockOnInternetException ex) {
                // impossible
            }
            CustomerInterface.this.refreshDisplay();
        }
    }

    private class BuyStockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<String> data = InputPanels.buySellStockPanel();
                if (InputValidChecker.inputNotNullChecker(data.get(1))) {
                    InvestmentAccount investmentAccount = (InvestmentAccount) activeAccount;
                    if (investmentAccount.buyStock(data.get(0), Integer.parseInt(data.get(1)))) {
                        JOptionPane.showMessageDialog(null, "Bought Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient Fund");
                    }
                }
            } catch (CancelException ex) {
                // Do nothing
            } catch (NoSuchStockOnInternetException ex) {
                JOptionPane.showMessageDialog(null, ex.response());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No internet connection");
            }
            CustomerInterface.this.refreshDisplay();
        }
    }


    private class SellStockListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                List<String> data = InputPanels.buySellStockPanel();
                if (InputValidChecker.inputNotNullChecker(data.get(1))) {
                    InvestmentAccount investmentAccount = (InvestmentAccount) activeAccount;
                    if (investmentAccount.sellStock(data.get(0), Integer.parseInt(data.get(1)))) {
                        JOptionPane.showMessageDialog(null, "Sold Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient stock");
                    }
                }
            } catch (CancelException ex) {
                // Do nothing
            } catch (NoSuchStockOnInternetException ex) {
                JOptionPane.showMessageDialog(null, ex.response());

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No internet connection");
            }
            CustomerInterface.this.refreshDisplay();
        }
    }

    private class SellAllStocksListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            InvestmentAccount investmentAccount = (InvestmentAccount) activeAccount;
            investmentAccount.sellAllStocks();
            CustomerInterface.this.refreshDisplay();
        }
    }

    private class AccountSectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (accountList.isSelectionEmpty()) {
                initializeDisplay();
            } else {
                activeAccount = accountList.getSelectedValue();
                refreshDisplay();
                findAccountButtons(activeAccount.getType());
            }
        }
    }

    private void refreshDisplay() {
        displayInfo.setText("Hello " + customer.getUserName() + "!\n"
                + "Here is a summary of your selected account:\n\n");
        displayInfo.append(activeAccount.accountInformation());
    }

    private void findAccountButtons(String type) {
        remove(controlPanel);
        initializeControlPanel();
        switch (type) {
            case "Debit": {
                findDebitButtons();
                break;
            }
            case "Credit": {
                findCreditButtons();
                break;
            }
            case "Investment":
            default: {
                findInvestmentButtons();
                break;
            }
        }
    }
}


