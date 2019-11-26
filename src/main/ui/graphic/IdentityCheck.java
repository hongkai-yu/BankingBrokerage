package ui.graphic;

import model.Bank;
import model.Customer;
import model.exception.DuplicationException;
import model.exception.UsernameOrPasswordWrongException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IdentityCheck extends JFrame {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private static final String FILE_PATH = "./data/Bank.txt";

    private Bank bank;
    private Boolean isSignUp;

    private JTextField usernameField;
    private JTextField passwordField;

    public static void main(String[] args) {
        new IdentityCheck();
    }

    private void initializeBank() {
        try {
            bank = Bank.load(FILE_PATH);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Broken bank file");
        }
    }

    public IdentityCheck() {
        super("Identity Checker");
        initializeBank();
        initializeWindow();
        isSignUp = false;

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JTextField();
        add(passwordField);

        JButton ok = new JButton("OK");
        ok.addActionListener(new SubmitForm());
        add(ok);

        JCheckBox signUp = new JCheckBox("Sign up");
        signUp.addActionListener(e -> isSignUp = signUp.isSelected());
        add(signUp);

        setVisible(true);
    }

    private void initializeWindow() {
        setLayout(new GridLayout(3, 2, 1, 1));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class SubmitForm implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (isSignUp) {
                try {
                    toBankSystem(bank.signUpCustomer(usernameField.getText(), passwordField.getText()));
                } catch (DuplicationException exception) {
                    JOptionPane.showMessageDialog(null, "Username already taken");
                }
            } else {
                try {
                    toBankSystem(bank.logInCustomer(usernameField.getText(), passwordField.getText()));
                } catch (UsernameOrPasswordWrongException exception) {
                    JOptionPane.showMessageDialog(null, "User or password incorrect");
                }
            }
        }

        private void toBankSystem(Customer customer) {
            IdentityCheck.this.dispose();
            new CustomerInterface(customer, bank);
        }
    }
}

