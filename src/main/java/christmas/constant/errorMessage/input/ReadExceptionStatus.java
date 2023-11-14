package christmas.constant.errorMessage.input;

import christmas.constant.errorMessage.ExceptionStatus;

public enum ReadExceptionStatus implements ExceptionStatus {

    DATE_IS_NOT_CORRECT("유효하지 않은 날짜입니다.");


    private static final String MESSAGE_PREFIX = "[ERROR] ";
    private static final String MESSAGE_SUFFIX = " 다시 입력해 주세요.";

    private final String message;

    ReadExceptionStatus(final String message) {
        this.message = MESSAGE_PREFIX + message + MESSAGE_SUFFIX;
    }

    public String getMessage() {
        return this.message;
    }
}
