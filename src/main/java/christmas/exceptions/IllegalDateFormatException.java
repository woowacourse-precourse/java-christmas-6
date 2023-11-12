package christmas.exceptions;

public class IllegalDateFormatException extends RestaurantException{

    private final static String DEFAULT_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public IllegalDateFormatException() {
        super(DEFAULT_MESSAGE);
    }
}
