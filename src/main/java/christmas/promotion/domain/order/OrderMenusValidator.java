package christmas.promotion.domain.order;

import christmas.promotion.exception.OrderMenuException;

import java.util.List;
import java.util.Map;

public class OrderMenusValidator {
    private static final int menuDetailsFactorSize = 2;
    private static final int menusMinimumSize = 1;
    private static final String DELIMITER = ",";

    public static void validateMenuDetailsCommaStartOrEnd(final String menuDetails) {
        if (menuDetails.startsWith(DELIMITER) || menuDetails.endsWith(DELIMITER)) {
            throw new OrderMenuException();
        }
    }

    public static void validateMenuDtailSize(final List<String> menuDetail) {
        if (menuDetail.size() != menuDetailsFactorSize) {
            throw new OrderMenuException();
        }
    }

    public static void validateMenuNameBlank(final String menuName) {
        if (menuName.isBlank() || menuName.contains(" ")) {
            throw new OrderMenuException();
        }
    }

    public static void validateMenuSizeInteger(final String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException exception) {
            throw new OrderMenuException();
        }
    }

    public static void validateMenuMinimumSize(final String number) {
        try {
            int parseNumber = Integer.parseInt(number);
            if (parseNumber < menusMinimumSize) {
                throw new OrderMenuException();
            }
        } catch (IllegalArgumentException exception) {
            throw new OrderMenuException();
        }
    }

    public static void validateMenuDuplicate(final Map<String, Integer> orderMenuDetails, final String orderMenuName) {
        for (String orderedMenuName : orderMenuDetails.keySet()) {
            if (orderedMenuName.equals(orderMenuName)) {
                throw new OrderMenuException();
            }
        }
    }
}
