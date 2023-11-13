package christmas.exceptions;

public class IllegalOnlyBeverageException extends RestaurantException {

    private final static String DEFAULT_MESSAGE = "죄송합니다. 음료만 주문하실 수는 없습니다. 다른 메뉴도 함께 주문하시는것은 어떠실까요?";

    public IllegalOnlyBeverageException() {
        super(DEFAULT_MESSAGE);
    }
}
