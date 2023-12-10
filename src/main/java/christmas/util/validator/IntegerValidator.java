package christmas.util.validator;

import static christmas.common.ExceptionMessage.ERROR_INVALID_INTEGER;
import static christmas.common.ExceptionMessage.ERROR_INVALID_RANGE;
import static christmas.common.ExceptionMessage.ERROR_NUMBER_NOT_IN_RANGE;

import java.util.regex.Pattern;

public class IntegerValidator {
    private static final Pattern VALID_INTEGER_PATTERN = Pattern.compile("^-?[1-9]\\d*|0$");

    public static void validateInteger(String input) {
        if (!isValidInteger(input)) {
            throw new IllegalArgumentException(ERROR_INVALID_INTEGER);
        }
    }

    private static boolean isValidInteger(String input) {
        return VALID_INTEGER_PATTERN.matcher(input).matches();
    }

    public static void validateRange(int input, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException(ERROR_INVALID_RANGE);
        }

        if (!isBetween(input, min, max)) {
            throw new IllegalArgumentException(String.format(ERROR_NUMBER_NOT_IN_RANGE, min, max));
        }
    }

    private static boolean isBetween(int input, int min, int max) {
        return (min <= input) && (input <= max);
    }
}
