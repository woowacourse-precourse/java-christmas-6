package christmas.exception;

public class InputException {
    public void dateInputError() {
        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    public void orderInputError() {
        System.out.println( "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
