package christmas.type;

public enum ErrorType {
    NOT_VALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    NOT_VALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_VALID_ORDER_ONLY_DRINK("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");

    private String text;

    ErrorType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
