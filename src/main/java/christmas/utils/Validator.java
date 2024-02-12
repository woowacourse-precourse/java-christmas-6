package christmas.utils;

import christmas.model.Menu;
import christmas.model.MenuType;

import java.util.Map;

public class Validator {

    public static void validateOrderForm(String orderStr) {
        String regex = "([가-힣a-zA-Z0-9]+-\\d+)(,[가-힣a-zA-Z0-9]+-\\d+)*";
        if(!orderStr.matches(regex)) throw new IllegalArgumentException(Exceptions.WRONG_ORDER_FORM.getMessage());
    }

    public static void validateOrderMenu(String menuName, Map<Menu, Integer> orderMap) {
        Menu currMenu = Menu.getEnumByName(menuName);
        if(currMenu == null) throw new IllegalArgumentException(Exceptions.NO_SUCH_MENU.getMessage());
        if(orderMap.get(currMenu) != null) throw new IllegalArgumentException(Exceptions.MENU_DUPLICATED.getMessage());
    }

    public static void validateOrderMap(Map<Menu, Integer> orderMap) {
        boolean allDrinks = orderMap.keySet().stream()
                .allMatch(menu -> menu.getType() == MenuType.DRINK);

        if(allDrinks) {
            throw new IllegalArgumentException("주문은 최소 하나 이상의 음료 외의 메뉴를 포함해야 합니다.");
        }
    }


    public static void isNumber(String str) {
        try {
            int temp = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(Exceptions.NOT_NUMBER.getMessage());
        }
    }

    public static void dateOutOfRange(String dateStr) {
        int temp = Integer.parseInt(dateStr);
        if(temp > 31) throw new IllegalArgumentException(Exceptions.NUMBER_OUT_OF_RANGE.getMessage());
        if(temp < 1) throw new IllegalArgumentException(Exceptions.NUMBER_OUT_OF_RANGE.getMessage());
    }

    public static void menuCountOutOfRange(Integer num) {
        if(num > 20 || num < 1) throw new IllegalArgumentException(Exceptions.MENU_COUNT_OUT_OF_RANGE.getMessage());
    }
}
