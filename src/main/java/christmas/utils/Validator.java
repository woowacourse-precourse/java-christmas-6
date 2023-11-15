package christmas.utils;

import christmas.exception.NotValidDateException;
import christmas.exception.NotValidMenuInputException;
import christmas.type.ErrorType;
import christmas.type.FoodType;
import christmas.type.MenuType;

import java.util.Arrays;
import java.util.Map;

public class Validator {
    public static final int DECEMBER_FIRST = 1;
    public static final int DECEMBER_LAST = 31;
    public static final int MIN_ORDER_COUNT = 1;
    public static final int MAX_ORDER_COUNT = 20;

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

    public static void checkMenuCountValid(int value) {
        if (value < MIN_ORDER_COUNT) {
            throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
        }
    }

    public static void checkMenuSumValid(Map<String, Integer> resultMap) {
        int sum = 0;
        int count = 0;
        for (String key : resultMap.keySet()) {
            sum += resultMap.get(key);
            if (MenuType.findFoodType(key).equals(FoodType.DRINK)) {
                count++;
            }
        }
        if (sum > MAX_ORDER_COUNT) {
            throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
        }

        if (count == resultMap.size()) {
            throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER_ONLY_DRINK.getText());
        }
    }
}
