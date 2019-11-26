package ui.graphic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InputPanels {

    //EFFECTS: return the type and name of the account the user wants to open
    public static List<String> openAccountPanel() throws CancelException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel typeLabel = new JLabel("Type:");
        String[] types = {"Debit", "Credit", "Investment"};
        JComboBox typeBox = new JComboBox(types);

        JLabel nameLabel = new JLabel("Account Name:");
        JTextField nameField = new JTextField();

        panel.add(typeLabel);
        panel.add(typeBox);
        panel.add(nameLabel);
        panel.add(nameField);

        cancelHandler(JOptionPane.showConfirmDialog(null, panel,
                "Open account", JOptionPane.OK_CANCEL_OPTION));

        List<String> result = new ArrayList<>();
        result.add(types[typeBox.getSelectedIndex()]);
        result.add(nameField.getText());
        return result;
    }

    //EFFECTS: return the stock code and type of the stock the user wants to buy or sell
    public static List<String> buySellStockPanel() throws CancelException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel codeLabel = new JLabel("Stock code:");
        JTextField codeField = new JTextField();

        JLabel shareLabel = new JLabel("Share:");
        JTextField shareField = new JTextField();

        panel.add(codeLabel);
        panel.add(codeField);
        panel.add(shareLabel);
        panel.add(shareField);

        cancelHandler(JOptionPane.showConfirmDialog(null, panel,
                "Stock broker", JOptionPane.OK_CANCEL_OPTION));

        List<String> result = new ArrayList<>();
        result.add(codeField.getText());
        result.add(shareField.getText());
        return result;
    }

    //EFFECTS: return the account and the amount of money the user wants to transfer
    public static List<String> transferMoneyPanel(Set<String> accounts) throws CancelException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel accountLabel = new JLabel("Account:");
        String[] payees = accounts.toArray(new String[0]);
        JComboBox payeeBox = new JComboBox(payees);

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        panel.add(accountLabel);
        panel.add(payeeBox);
        panel.add(amountLabel);
        panel.add(amountField);

        cancelHandler(JOptionPane.showConfirmDialog(null, panel,
                "Transfer money", JOptionPane.OK_CANCEL_OPTION));

        List<String> result = new ArrayList<>();
        result.add(payees[payeeBox.getSelectedIndex()]);
        result.add(amountField.getText());
        return result;
    }

    private static void cancelHandler(int option) throws CancelException {
        if (option == JOptionPane.CANCEL_OPTION) {
            throw new CancelException();
        }
    }
}
