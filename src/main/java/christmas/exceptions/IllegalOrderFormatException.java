package christmas.exceptions;

public class IllegalOrderFormatException extends RestaurantException{

    private final static String DEFAULT_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public IllegalOrderFormatException() {
        super(DEFAULT_MESSAGE);
    }
}
