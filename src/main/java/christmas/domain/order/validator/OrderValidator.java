package christmas.domain.order.validator;

import christmas.constant.errorMessage.exception.CustomIllegalArgumentException;
import christmas.constant.errorMessage.input.EventExceptionStatus;
import java.util.Map;

public class OrderValidator {

    private static final OrderValidator ORDER_VALIDATOR = new OrderValidator();

    private OrderValidator() {
    }

    public static void validateOrder(final Map<String, Integer> menus) {
        ORDER_VALIDATOR.validateOrderIsValidQuantity(menus);
    }

    private void validateOrderIsValidQuantity(final Map<String, Integer> menus) {
        if (checkOrderIsValidQuantity(menus)) {
            throw new CustomIllegalArgumentException(EventExceptionStatus.MENU_IS_NOT_CORRECT);
        }
    }

    private boolean checkOrderIsValidQuantity(final Map<String, Integer> menus) {
        final int totalQuantity = menus.keySet()
                .stream()
                .mapToInt(menus::get)
                .sum();
        return totalQuantity > 20;
    }


}
