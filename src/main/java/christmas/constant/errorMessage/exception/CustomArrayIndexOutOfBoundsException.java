package christmas.constant.errorMessage.exception;

import christmas.constant.errorMessage.ExceptionStatus;

public class CustomArrayIndexOutOfBoundsException extends ArrayIndexOutOfBoundsException {

    public CustomArrayIndexOutOfBoundsException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
    }
}
