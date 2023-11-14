package christmas.constant.errorMessage.exception;

import christmas.constant.errorMessage.ExceptionStatus;

public class CustomIllegalArgumentException extends IllegalArgumentException {

    public CustomIllegalArgumentException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
    }
}
