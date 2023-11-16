package christmas.promotion.exception;

/**
 * 현재 메뉴 에서는 발생하지 않는 에러
 * 하지만 메뉴에 2500원 디저트가 추가되고
 * 2023-12-25에 2500원 디저트 10개이상을 주문하면 "할인 된 최종 가격"이 "음수"다.
 * 이 때 발생하는 예외
 */

public class OrderEventException extends IllegalArgumentException {
    public enum ErrorMessage {
        ORDER_EVENT_ERROR("[ERROR] 할인을 적용한 총 가격이 0원 보다 커야합니다. 다시 입력해 주세요.");

        ErrorMessage(final String message) {
            this.message = message;
        }

        private final String message;

        public String getMessage() {
            return message;
        }
    }

    public OrderEventException() {
        super(ErrorMessage.ORDER_EVENT_ERROR.getMessage());
    }
}