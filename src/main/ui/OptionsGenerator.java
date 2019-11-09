package ui;

import java.util.List;

public class OptionsGenerator {

    public static String generateOptions(List<String> options) {
        StringBuilder result = new StringBuilder();
        int index = 1;
        for (String option : options) {
            result.append("[").append(index).append("] ").append(option).append(" ");
            index++;
        }
        return result.toString();
    }
}
