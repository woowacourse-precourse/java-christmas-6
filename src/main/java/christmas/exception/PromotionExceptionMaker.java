package christmas.exception;

public enum PromotionExceptionMaker {
    INVALID_NUMBER_FORMAT("숫자를 입력해 주세요"),
    INVALID_INPUT_FORMAT("입력 형식이 잘못되었습니다."),

    BLANK_INPUT("입력값이 비어 있습니다."),
    NO_SUCH_MENU("잘못된 메뉴 입력입니다."),
    MENU_AMOUNT_MUST_POSITIVE("상품 수량은 양수여야 합니다."),
    MONEY_MUST_NOT_NEGATIVE("금액은 0 이상이어야 합니다."),
    NOT_ENOUGH_MONEY("잔액이 부족합니다.");

    private final String message;
    private final IllegalArgumentException exception;

    PromotionExceptionMaker(String message) {
        this.message = message;
        this.exception = new IllegalArgumentException(this.message);
    }

    public IllegalArgumentException makeException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
