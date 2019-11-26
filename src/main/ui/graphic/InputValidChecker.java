package ui.graphic;


import javax.swing.*;

public class InputValidChecker {
    public static Boolean inputNotNullChecker(String input) {
        if (input == null || input.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the required field!");
            return false;
        } else {
            return true;
        }
    }
}
