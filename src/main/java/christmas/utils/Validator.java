package christmas.utils;

import christmas.exception.NotValidDateException;
import christmas.type.ErrorType;

public class Validator {

    public static final int DECEMBER_FIRST = 1;
    public static final int DECEMBER_LAST = 31;

    public static void checkDateValid(int date) {
        if (date < DECEMBER_FIRST || date > DECEMBER_LAST) {
            throw new NotValidDateException(ErrorType.NOT_VALID_DATE.getText());
        }
    }
}
