package christmas.utils;

import christmas.exception.NotValidDateException;
import christmas.exception.NotValidMenuInputException;
import christmas.type.ErrorType;
import christmas.type.MenuType;

import java.util.Arrays;

public class Validator {

    public static final int DECEMBER_FIRST = 1;
    public static final int DECEMBER_LAST = 31;

    public static void checkDateValid(int date) {
        if (date < DECEMBER_FIRST || date > DECEMBER_LAST) {
            throw new NotValidDateException(ErrorType.NOT_VALID_DATE.getText());
        }
    }

    public static void checkFoodValid(String foodName) {
        boolean isExist = Arrays.stream(MenuType.values())
                .anyMatch(v -> v.getFoodName().equals(foodName));
        if (!isExist) {
            throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
        }
    }
}
