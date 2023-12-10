package christmas.util.validator;

public class InputVisitDayValidator {
    public static void validateVisitDay(String input) {
        BlankValidator.validate(input);
        IntegerValidator.validateInteger(input);
    }
}
