package christmas.util.validator;

import static christmas.common.ExceptionMessage.ERROR_INVALID_ORDER_FORMAT;

import christmas.util.converter.StringConverter;
import java.util.regex.Pattern;

public class InputOrderValidator {
    private static final Pattern ORDER_FORMAT_PATTERN = Pattern.compile("^\\s*[ㄱ-ㅎ가-힣]+\\s*-\\s*\\d+\\s*$");

    public static void validateOrder(String input) {
        BlankValidator.validate(input);
        validateRawOrders(input);
    }

    private static void validateRawOrders(String input) {
        if (isOrderInvalidFormat(input)) {
            throw new IllegalArgumentException(ERROR_INVALID_ORDER_FORMAT);
        }
    }

    private static boolean isOrderInvalidFormat(String input) {
        return StringConverter.splitStringByComma(input)
                .stream()
                .map(String::trim)
                .anyMatch(order -> !ORDER_FORMAT_PATTERN.matcher(order).matches());
    }
}
