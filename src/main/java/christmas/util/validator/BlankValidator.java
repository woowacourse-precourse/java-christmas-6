package christmas.util.validator;

import static christmas.common.ExceptionMessage.ERROR_BLANK_VALUE;

public class BlankValidator {
    public static void validate(String input) {
        if (isBlank(input)) {
            throw new IllegalArgumentException(ERROR_BLANK_VALUE);
        }
    }

    private static boolean isBlank(String input) {
        return input.isBlank();
    }
}
