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
        Map<String, Integer> resultMap = new HashMap<>();
        String[] commaSplits = checkSplit(input);

        for (String s : commaSplits) {
            String[] split = s.split("-");
            String key = split[0];
            int value = Integer.parseInt(split[1]);
            checkMenuInput(resultMap, key, value);
            resultMap.put(key, value);
        }

        return resultMap;
    }

    private static void checkMenuInput(Map<String, Integer> resultMap, String key, int value) {
        Validator.checkMenuCountValid(resultMap, value);
        if (resultMap.containsKey(key)) {
            throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
        }
    }

    private static String[] checkSplit(String input) {
        String[] commaSplits = input.split(",");
        for (String commaSplit : commaSplits) {
            if (!Pattern.matches(MENU_COUNT_REGEX, commaSplit)) {
                throw new NotValidMenuInputException(ErrorType.NOT_VALID_ORDER.getText());
            }
        }
        return commaSplits;
    }

    public static void convertListToMap(HashMap<String, Integer> orderMap, List<Food> foodList) {
        for (Food food : foodList) {
            orderMap.put(food.getFoodName(), food.getCount());
        }
    }

}
