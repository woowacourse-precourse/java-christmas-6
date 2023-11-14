package christmas.type;

public enum ErrorType {
    NOT_VALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private String text;

    ErrorType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
