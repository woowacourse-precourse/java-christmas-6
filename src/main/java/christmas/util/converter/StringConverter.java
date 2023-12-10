package christmas.util.converter;

import static christmas.common.ExceptionMessage.ERROR_INVALID_INTEGER;

import java.util.Arrays;
import java.util.List;

public class StringConverter {
    public static Integer convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_INVALID_INTEGER);
        }
    }

    public static List<String> splitStringByComma(String input) {
        return Arrays.stream(input.split(","))
                .toList();
    }
}
