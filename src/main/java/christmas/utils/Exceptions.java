package christmas.utils;

public enum Exceptions {
    NOT_NUMBER("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    NUMBER_OUT_OF_RANGE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NO_SUCH_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    WRONG_ORDER_FORM("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MENU_COUNT_OUT_OF_RANGE("[ERROR] 주문 수량은 메뉴당 1개 이상 20개 이하여야 합니다. 전체 수량은 20개 이하여야 합니다."),
    MENU_DUPLICATED("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private String message;

    Exceptions(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
