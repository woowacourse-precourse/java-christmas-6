package christmas.view.input.validator;

import christmas.constant.errorMessage.exception.CustomArrayIndexOutOfBoundsException;
import christmas.constant.errorMessage.exception.CustomIllegalArgumentException;
import christmas.constant.errorMessage.exception.CustomIllegalStateException;
import christmas.constant.errorMessage.exception.CustomNullPointException;
import christmas.constant.errorMessage.exception.CustomNumberFormatException;
import christmas.constant.errorMessage.input.EventExceptionStatus;
import christmas.utils.Delimiter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderValidator {

    private static final OrderValidator ORDER_VALIDATOR = new OrderValidator();

    private OrderValidator() {
    }

    public static Map<String, Integer> validateOrder(final String order) {
        return ORDER_VALIDATOR.validateOrderIsCorrect(order);
    }

    private Map<String, Integer> validateOrderIsCorrect(final String order) {
        try {
            return validateOrderIsDuplicated(order);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CustomArrayIndexOutOfBoundsException(EventExceptionStatus.MENU_IS_NOT_CORRECT);
        }
    }

    private Map<String, Integer> validateOrderIsDuplicated(final String order) {
        try {
            return parseOrder(order);
        } catch (IllegalStateException e) {
            throw new CustomIllegalStateException(EventExceptionStatus.MENU_IS_NOT_CORRECT);
        }
    }

    private Map<String, Integer> parseOrder(final String order) {
        return Arrays.stream(Delimiter.splitWithComma(validateOrderIsNull(order)))
                .map(Delimiter::splitWithBar)
                .collect(Collectors.toMap(
                        menu -> validateBothEndsIsBlank(menu[0]),
                        quantity -> validateQuantityIsNumeric(quantity[1])
                ));
    }

    private String validateOrderIsNull(final String order) {
        try {
            return order.trim();
        } catch (NullPointerException e) {
            throw new CustomNullPointException(EventExceptionStatus.MENU_IS_NOT_CORRECT);
        }
    }

    private int validateQuantityIsNumeric(final String order) {
        try {
            return Integer.parseInt(order);
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatException(EventExceptionStatus.MENU_IS_NOT_CORRECT);
        }
    }

    private String validateBothEndsIsBlank(final String order) {
        if (!isBothEndsBlank(order)) {
            return order;
        }
        throw new CustomIllegalArgumentException(EventExceptionStatus.MENU_IS_NOT_CORRECT);
    }

    private boolean isBothEndsBlank(final String order) {
        return validateOrderIsNull(order).length() != order.length();
    }
}
