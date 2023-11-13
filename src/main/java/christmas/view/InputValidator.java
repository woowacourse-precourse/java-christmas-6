package christmas.view;

public class InputValidator {
    private static final String ERROR_PREFIX = "[ERROR]";
    private static final String INTEGER_REGEX = "^-?\\d+$";

    public void validateDateInput (String input) throws IllegalArgumentException {
        isInteger(input);

        int date = Integer.parseInt(input);
        isDateInDecember(date);
    }

    private void isInteger (String input) {
        if (!input.matches(INTEGER_REGEX)) {
            throw new IllegalArgumentException(ERROR_PREFIX + "정수 입력");
        }
    }

    private void isDateInDecember (int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ERROR_PREFIX + "1부터 31 이내의 날짜 입력");
        }
    }
}
