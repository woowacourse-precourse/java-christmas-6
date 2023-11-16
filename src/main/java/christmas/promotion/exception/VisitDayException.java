package christmas.promotion.exception;

public class VisitDayException extends IllegalArgumentException {
    public enum ErrorMessage {
        VISIT_DAY_ERROR("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");

        ErrorMessage(final String message) {
            this.message = message;
        }

        private final String message;

        public String getMessage() {
            return message;
        }
    }

    public VisitDayException() {
        super(ErrorMessage.VISIT_DAY_ERROR.getMessage());
    }
}
