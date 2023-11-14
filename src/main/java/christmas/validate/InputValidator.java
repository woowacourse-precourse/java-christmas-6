package christmas.validate;

import christmas.model.Menu;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String INTEGER_REGEX = "^-?\\d+$";
    private static final int MAX_ORDER_PLATE = 20;
    private static final String FORMAT_TO_ORDER_MENU_REX = "([가-힣]+)-(\\d+)";
    private static final String DRINK = "음료";

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

    public SimpleEntry<Menu, Integer> validateOrderStateFormat (List<Menu> menu, String menuOrdered) throws IllegalArgumentException {
        SimpleEntry<Menu, Integer> result = null;

        Pattern pattern = Pattern.compile(FORMAT_TO_ORDER_MENU_REX);
        Matcher matcher = pattern.matcher(menuOrdered);

        if (!matcher.find()) {
            throw new IllegalArgumentException(ERROR_PREFIX + " 메뉴 입력 형식에 올바르게 작성해주세요.");
        }

        int cnt = Integer.parseInt(matcher.group(2));
        Menu matchedMenu = checkValidMenu(menu, matcher.group(1));
        result = new SimpleEntry<>(matchedMenu, cnt);
        return result;

    }

    public void hasReachedMaxOrderItems (List<SimpleEntry<Menu, Integer>> orderedList) {
        int numberOfOrderedMenu = orderedList.stream().mapToInt(SimpleEntry::getValue).sum();
        if (numberOfOrderedMenu > MAX_ORDER_PLATE) {
            throw new IllegalStateException(ERROR_PREFIX + "주문가능한 메뉴는 총 20개 이하입니다.");
        }
    }

    public Menu checkValidMenu (List<Menu> menu, String menuName) throws IllegalArgumentException {
        try {
            return menu.stream().filter(el -> el.checkMenu(menuName)).toList().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw  new IllegalArgumentException(ERROR_PREFIX + " 메뉴판에 있는 메뉴를 선택해주세요.");
        }
    }

    public void checkCategory (List<SimpleEntry<Menu, Integer>> orderedList) throws IllegalArgumentException {
        boolean isAnyMatchedExceptDrink =  orderedList.stream().anyMatch(el -> !el.getKey().checkCategory(DRINK));
        if (!isAnyMatchedExceptDrink) {
            throw new IllegalArgumentException(ERROR_PREFIX + " 디저트류만 이용하실 수 없습니다.");
        }
    }

}
