package christmas.domain.date.validator;

import christmas.constant.errorMessage.exception.CustomIllegalArgumentException;
import christmas.constant.errorMessage.input.EventExceptionStatus;

public class DateValidator {

    private static final DateValidator DATE_VALIDATOR = new DateValidator();
    private static final int ALLOWED_MINIMUM_DATE = 1;
    private static final int ALLOWED_MAXIMUM_DATE = 31;

    private DateValidator() {
    }

    public static void validateDate(final int date) {
        DATE_VALIDATOR.validateDateIsOutOfRange(date);
    }

    private void validateDateIsOutOfRange(final int date) {
        if (isOutOfRange(date)) {
            throw new CustomIllegalArgumentException(EventExceptionStatus.DATE_IS_NOT_CORRECT);
        }
    }

    private boolean isOutOfRange(final int date) {
        return date < ALLOWED_MINIMUM_DATE
                || ALLOWED_MAXIMUM_DATE < date;
    }
}
