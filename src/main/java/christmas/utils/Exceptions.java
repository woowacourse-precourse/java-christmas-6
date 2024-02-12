package christmas.utils;

public enum Exceptions {
    NOT_NUMBER("[ERROR] 올바르지 않은 입력 값입니다. 숫자를 입력해주세요."),
    NUMBER_OUT_OF_RANGE("[ERROR] 올바르지 않은 입력 값입니다. 정해진 범위 내의 숫자를 입력해주세요."),
    NO_SUCH_MENU("[ERROR] 올바르지 않은 입력 값입니다. 메뉴판에 있는 메뉴를 입력해주세요."),
    WRONG_ORDER_FORM("[ERROR] 올바르지 않은 입력 양식입니다. 정해진 양식에 맞춰 입력해주세요."),
    MENU_COUNT_OUT_OF_RANGE("[ERROR] 주문 수량은 메뉴당 1개 이상 20개 이하여야 합니다. 전체 수량은 20개 이하여야 합니다."),
    MENU_DUPLICATED("[ERROR] 메뉴는 중복될 수 없습니다.");

    private String message;

    Exceptions(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
