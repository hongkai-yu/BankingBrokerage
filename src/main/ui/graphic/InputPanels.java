package ui.graphic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InputPanels {
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
                "Please enter the type and the name", JOptionPane.OK_CANCEL_OPTION));

        List<String> result = new ArrayList<>();
        result.add(types[typeBox.getSelectedIndex()]);
        result.add(nameField.getText());
        return result;
    }

    public static List<String> buySellStockPanel() throws CancelException {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel codeLabel = new JLabel("Stock code:");
        JTextField codeField = new JTextField();

        JLabel shareLabel = new JLabel("share:");
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

    private static void cancelHandler(int option) throws CancelException {
        if (option == JOptionPane.CANCEL_OPTION) {
            throw new CancelException();
        }
    }
}
