package christmas.utils;

import christmas.domain.Food;
import christmas.exception.NotValidDateException;
import christmas.type.ErrorType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    public static int convertToInt(String input) {
        if (!Pattern.matches("^[1-9]\\d*$", input)) {
            throw new NotValidDateException(ErrorType.NOT_VALID_DATE.getText());
        }
        return Integer.parseInt(input);
    }

    public static Map<String, Integer> convertToMap(String input) {
        if (!Pattern.matches("^([a-zA-Z가-힣]+-\\d+,)*([a-zA-Z가-힣]+-\\d+)$\n", input)) {
            throw new IllegalArgumentException(ErrorType.NOT_VALID_ORDER.getText());
        }

        return Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(a -> a[0], a -> Integer.parseInt(a[1])));
    }

    public static void convertListToMap(HashMap<String, Integer> orderMap, List<Food> foodList) {
        for (Food food : foodList) {
            orderMap.put(food.getFoodName(), food.getCount());
        }
    }

}
