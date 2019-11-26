package ui.graphic;


import javax.swing.*;

public class InputValidChecker {
    //EFFECTS: to make sure the input is neither null or no content
    public static Boolean inputNotNullChecker(String input) {
        if (input == null || input.equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the required field!");
            return false;
        } else {
            return true;
        }
    }
}
