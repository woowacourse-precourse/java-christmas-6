package christmas.constant.errorMessage.exception;

import christmas.constant.errorMessage.ExceptionStatus;

public class CustomNumberFormatException extends NumberFormatException {

    public CustomNumberFormatException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
    }
}
