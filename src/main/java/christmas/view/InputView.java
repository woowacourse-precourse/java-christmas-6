package christmas.view;

import christmas.exception.PromotionExceptionMaker;
import christmas.view.io.Printer;
import christmas.view.io.Reader;
import java.util.List;
import java.util.regex.Pattern;

public class InputView {
    public static final Pattern MENU_PATTERN = Pattern.compile("[가-힣]+-\\d+");
    public static final String DELIMITER = ",";

    public static int getVisitDay() {
        Printer.printMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Reader.getInteger();
    }

    public static List<String> getMenus() {
        Printer.printMessage("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        List<String> orders = Reader.getStringsUsingDelimiter(DELIMITER);
        validateOrders(orders);
        return orders;
    }

    private static void validateOrders(List<String> orders) {
        if (notMatchPattern(orders)) {
            throw PromotionExceptionMaker.INVALID_INPUT_FORMAT.makeException();
        }
    }

    private static boolean notMatchPattern(List<String> orders) {
        return orders.stream()
                .anyMatch(order -> !MENU_PATTERN.matcher(order).matches());
    }

    public static void closeConsole() {
        Reader.close();
    }
}
