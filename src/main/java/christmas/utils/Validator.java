package christmas.utils;

public class Validator {
    public static void checkDateValid(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }
}
