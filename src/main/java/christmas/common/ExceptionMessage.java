package christmas.common;

public final class ExceptionMessage {
    public static final String ERROR_BLANK_VALUE = "[ERROR] 값이 공백 입니다.";

    public static final String ERROR_INVALID_INTEGER = "[ERROR] 값이 유효한 정수가 아닙니다.";
    public static final String ERROR_INVALID_RANGE = "[ERROR] 유효한 범위가 아닙니다.";
    public static final String ERROR_NUMBER_NOT_IN_RANGE = "[ERROR] 값이 %d에서 %d 사이의 정수가 아닙니다.";

    public static final String ERROR_INVALID_MENU_SELECTION = "[ERROR] 해당하는 메뉴가 없습니다.";
    public static final String ERROR_DUPLICATE_MENU_SELECTION = "[ERROR] 메뉴가 중복됩니다.";
    public static final String ERROR_INVALID_ORDER_FORMAT = "[ERROR] 주문 형식이 잘못 되었습니다.";
    public static final String ERROR_MENU_COUNT_LESS_THAN_MINIMUM = "[ERROR] 메뉴의 개수는 %d개 이상이어야 합니다.";
    public static final String ERROR_MENU_COUNT_MAXIMUM_EXCEEDED = "[ERROR] 총 메뉴 개수가 %d를 초과합니다.";
    public static final String ERROR_MENU_ONLY_BEVERAGE = "[ERROR] 음료만 주문할 수 없습니다.";
}
