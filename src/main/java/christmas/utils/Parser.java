package christmas.utils;

import java.util.regex.Pattern;

public class Parser {
    public static int convertToInt(String input) {
        if (!Pattern.matches("^[1-9]\\d*$", input)) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(input);
    }
}
