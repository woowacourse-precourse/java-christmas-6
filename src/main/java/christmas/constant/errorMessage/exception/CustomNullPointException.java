package christmas.constant.errorMessage.exception;

import christmas.constant.errorMessage.ExceptionStatus;

public class CustomNullPointException extends NullPointerException {

    public CustomNullPointException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
    }
}
