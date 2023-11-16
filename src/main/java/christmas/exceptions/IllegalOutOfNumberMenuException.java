package christmas.exceptions;

public class IllegalOutOfNumberMenuException extends RestaurantException {

    private final static String DEFAULT_MESSAGE = "메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n"
            + "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)";

    public IllegalOutOfNumberMenuException() {
        super(DEFAULT_MESSAGE);
    }
}
