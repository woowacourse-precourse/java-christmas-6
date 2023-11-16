package christmas.utils;

import java.text.NumberFormat;

public class NumberFormatter {
    private final static NumberFormat format = NumberFormat.getNumberInstance();

    public static String formatting(int number) {
        return format.format(number);
    }
}
