package christmas.view.input;

import christmas.view.input.reader.Reader;
import christmas.view.input.validator.DateValidator;
import christmas.view.input.validator.OrderValidator;
import java.util.Map;

public class InputView {

    private static final String PRINT_READ_DATE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String PRINT_READ_MENU_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. "
            + "(e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    private final Reader reader;

    public InputView(final Reader reader) {
        this.reader = reader;
    }

    private String readLine() {
        return reader.readLine();
    }

    public int readDate() {
        System.out.println(PRINT_READ_DATE_MESSAGE);
        return DateValidator.validateDate(readLine());
    }

    public Map<String, Integer> readOrder() {
        System.out.println(PRINT_READ_MENU_MESSAGE);
        return OrderValidator.validateOrder(readLine());
    }
}
