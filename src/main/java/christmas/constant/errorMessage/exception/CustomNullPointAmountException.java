package christmas.constant.errorMessage.exception;

import christmas.constant.errorMessage.ExceptionStatus;

public class CustomNullPointAmountException extends NullPointerException {

    public CustomNullPointAmountException(ExceptionStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
    }
}
