package christmas.type;

public enum TextType {
    START_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    RESTAURANT_DATE_INPUT_MESSAGE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    MENU_ORDER_INPUT_MESSAGE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    SHOW_BENEFIT_OUTPUT_MESSAGE("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_OUTPUT_MESSAGE("<주문 메뉴>"),
    BEFORE_DISCOUNT_ORDER_PRICE("<할인 전 총주문 금액>"),
    PRESENTATION_MENU("<증정 메뉴>"),
    BENEFIT_HISTORY("<혜택 내역>"),
    TOTAL_BENEFIT_PRICE("<총혜택 금액>"),
    AFTER_DISCOUNT_PRICE("<할인 후 예상 결제 금액>"),
    EVENT_BADGE("<12월 이벤트 배지>");

    private String text;

    TextType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
