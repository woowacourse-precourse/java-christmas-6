package christmas.constant.errorMessage.exception;

import christmas.constant.errorMessage.ExceptionStatus;

public class CustomIllegalStateException extends IllegalStateException {

    public CustomIllegalStateException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
    }
}
