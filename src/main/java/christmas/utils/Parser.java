package christmas.utils;

import christmas.domain.Food;
import christmas.exception.NotValidDateException;
import christmas.exception.NotValidMenuInputException;
import christmas.type.ErrorType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

    public static final String COMMA_REGEX = "^([^,]+,)*[^,]+$";
    public static final String INTEGER_REGEX = "^[1-9]\\d*$";
    public static final String MENU_COUNT_REGEX = "^[a-zA-Z가-힣]+-\\d+$";

    public static int convertToInt(String input) {
        if (!Pattern.matches(INTEGER_REGEX, input)) {
            throw new NotValidDateException(ErrorType.NOT_VALID_DATE.getText());
        }
        return Integer.parseInt(input);
    }

    public static Map<String, Integer> convertToMap(String input) {
        if (!Pattern.matches(COMMA_REGEX, input)) {
            throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
        }

        String[] commaSplits = input.split(",");
        for (String commaSplit : commaSplits) {
            if (!Pattern.matches(MENU_COUNT_REGEX, commaSplit)) {
                throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
            }
        }

        return Arrays.stream(commaSplits)
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(a -> a[0], a -> Integer.parseInt(a[1])));
    }

    public static void convertListToMap(HashMap<String, Integer> orderMap, List<Food> foodList) {
        for (Food food : foodList) {
            orderMap.put(food.getFoodName(), food.getCount());
        }
    }

}
