package christmas.promotion.exception;

public class OrderMenuException extends IllegalArgumentException {
    public enum ErrorMessage {
        ORDER_MENU_ERROR("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");

        ErrorMessage(final String message) {
            this.message = message;
        }

        private final String message;

        public String getMessage() {
            return message;
        }
    }

    public OrderMenuException() {
        super(ErrorMessage.ORDER_MENU_ERROR.getMessage());
    }
}
