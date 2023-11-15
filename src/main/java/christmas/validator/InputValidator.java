package christmas.validator;

import christmas.model.Menu;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static final String ERROR_CAUSED_BY_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String ERROR_CAUSED_BY_ORDER =  "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String INTEGER_REGEX = "^-?\\d+$";
    private static final int MAX_ORDER_PLATE = 20;
    private static final String FORMAT_TO_ORDER_MENU_REX = "([가-힣]+)-(\\d+)";
    private static final String DRINK = "음료";

    public void validateDateInput (String input) throws IllegalArgumentException {
        boolean isInteger = isInteger(input);
        if (!isInteger) {
            throw new IllegalArgumentException(ERROR_CAUSED_BY_DATE);
        }

        int date = Integer.parseInt(input);
        boolean isDateInDecember = isDateInDecember(date);
        if(!isDateInDecember) {
            throw new IllegalArgumentException(ERROR_CAUSED_BY_DATE);
        }
    }

    private boolean isInteger (String input) {
        return input.matches(INTEGER_REGEX);
    }

    private boolean isDateInDecember (int date)  {
        return  date >= 1 && date <= 31;
    }

    public SimpleEntry<Menu, Integer> validateOrderStateFormat (List<Menu> menu, String menuOrdered) throws IllegalArgumentException {
        SimpleEntry<Menu, Integer> result;

        Pattern pattern = Pattern.compile(FORMAT_TO_ORDER_MENU_REX);
        Matcher matcher = pattern.matcher(menuOrdered);

        if (!matcher.find()) {
            throw new IllegalArgumentException(ERROR_CAUSED_BY_ORDER);
        }

        int cnt = Integer.parseInt(matcher.group(2));
        Menu matchedMenu = checkValidMenu(menu, matcher.group(1));
        result = new SimpleEntry<>(matchedMenu, cnt);
        return result;

    }

    public void checkDuplicatedMenus (List<SimpleEntry<Menu, Integer>> orderedList) throws IllegalArgumentException {
        int distictedMenuList = orderedList.stream().distinct().toList().size();
        int originMenuList = orderedList.size();

        if (distictedMenuList != originMenuList) {
            throw new IllegalStateException(ERROR_CAUSED_BY_ORDER);
        }
    }

    public void hasReachedMaxOrderItems (List<SimpleEntry<Menu, Integer>> orderedList) throws IllegalArgumentException {
        int numberOfOrderedMenu = orderedList.stream().mapToInt(SimpleEntry::getValue).sum();
        if (numberOfOrderedMenu > MAX_ORDER_PLATE) {
            throw new IllegalStateException(ERROR_CAUSED_BY_ORDER);
        }
    }

    public Menu checkValidMenu (List<Menu> menu, String menuName) throws IllegalArgumentException {
        try {
            return menu.stream().filter(el -> el.checkMenu(menuName)).toList().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw  new IllegalArgumentException(ERROR_CAUSED_BY_ORDER);
        }
    }

    public void checkCategory (List<SimpleEntry<Menu, Integer>> orderedList) throws IllegalArgumentException {
        boolean isAnyMatchedExceptDrink =  orderedList.stream().anyMatch(el -> !el.getKey().checkCategory(DRINK));
        if (!isAnyMatchedExceptDrink) {
            throw new IllegalArgumentException(ERROR_CAUSED_BY_ORDER);
        }
    }

}
