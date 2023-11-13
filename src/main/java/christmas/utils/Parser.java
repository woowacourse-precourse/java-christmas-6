package christmas.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    public static int convertToInt(String input) {
        if (!Pattern.matches("^[1-9]\\d*$", input)) {
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(input);
    }

    public static Map<String, Integer> convertToMap(String input) {
        if (!Pattern.matches("^([a-zA-Z0-9가-힣]+\\-[0-9]+,)*([a-zA-Z0-9가-힣]+\\-[0-9]+)$", input)) {
            throw new IllegalArgumentException();
        }

        return Arrays.stream(input.split(","))
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(a -> a[0], a -> Integer.parseInt(a[1])));
    }
}
