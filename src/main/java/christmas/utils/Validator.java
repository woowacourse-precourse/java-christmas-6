package christmas.utils;

import christmas.exception.NotValidDateException;
import christmas.exception.NotValidMenuInputException;
import christmas.type.ErrorType;
import christmas.type.FoodType;
import christmas.type.MenuType;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

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

    public static void checkMenuCountValid(Map<String, Integer> resultMap, int value) {
        if (value < 1) {
            throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
        }
    }

    public static void checkMenuSumValid(Map<String, Integer> resultMap) {
        int sum = 0;
        Set<String> keySet = resultMap.keySet();
        for (String key : keySet) {
            sum += resultMap.get(key);
            if (sum > 20) {
                throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
            }

            if (keySet.size() == 1 && MenuType.findFoodType(key).equals(FoodType.DRINK)) {
                throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER_ONLY_DRINK.getText());
            }
        }

    }
}
