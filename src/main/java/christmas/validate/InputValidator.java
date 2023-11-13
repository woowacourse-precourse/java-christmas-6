package christmas.validate;

import christmas.model.Menu;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String INTEGER_REGEX = "^-?\\d+$";
    private static final int MAX_ORDER_PLATE = 20;
    private static final String FORMAT_TO_ORDER_MENU_REX = "([가-힣]+)-(\\d+)";

    public void validateDateInput (String input) throws IllegalArgumentException {
        boolean isInteger = isInteger(input);
        if (!isInteger) {
            throw new IllegalArgumentException(ERROR_PREFIX + "정수 입력");
        }

        int date = Integer.parseInt(input);
        boolean isDateInDecember = isDateInDecember(date);
        if(!isDateInDecember) {
            throw new IllegalArgumentException(ERROR_PREFIX + "1부터 31 이내의 날짜 입력");
        }
    }

    private boolean isInteger (String input) {
        return input.matches(INTEGER_REGEX);
    }

    private boolean isDateInDecember (int date)  {
        return  date >= 1 && date <= 31;
    }

    public Matcher validateOrderStateFormat (String menuOrdered) {
        Pattern pattern = Pattern.compile(FORMAT_TO_ORDER_MENU_REX);
        Matcher matcher = pattern.matcher(menuOrdered);
        matcher.find();
        return matcher;
    }

    public void hasReachedMaxOrderItems (List<SimpleEntry<Menu, Integer>> orderedList) {
        int numberOfOrderedMenu = orderedList.stream().mapToInt(SimpleEntry::getValue).sum();
        if (numberOfOrderedMenu > MAX_ORDER_PLATE) {
            throw new IllegalStateException(ERROR_PREFIX + "주문가능한 메뉴는 총 20개 이하입니다.");
        }
    }

}
