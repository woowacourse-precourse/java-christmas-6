package christmas.view;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.domain.constants.Menu;
import christmas.global.exception.CustomException;
import christmas.global.exception.ErrorMessage;
import christmas.view.console.ConsoleReader;
import christmas.view.console.ConsoleWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    private static final String ORDER_SEPARATOR = ",";
    private static final String ORDER_INFO_SEPARATOR = "-";


    public int readDay() {
        ConsoleWriter.printlnMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Validator.validateDay(ConsoleReader.enterMessage());
    }

    public Orders readOrders() {
        ConsoleWriter.printlnMessage("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return new Orders(
                Validator.validateOrders(ConsoleReader.enterMessage())
        );
    }

    private static class Validator {
        public static int validateDay(String message) {
            int number = validateNumber(message, ErrorMessage.INVALID_DAY_ERROR);
            validateRange(number, START_DAY, END_DAY);
            return number;
        }

        public static int validateNumber(String message, ErrorMessage errorMessage) {
            if (isNotNumber(message)) {
                throw CustomException.from(errorMessage);
            }
            return Integer.parseInt(message);
        }

        private static boolean isNotNumber(String str) {
            return !str.matches("\\d+");
        }

        public static void validateRange(int number, int start, int end) {
            if (isInvalidRange(number, start, end)) {
                throw CustomException.from(ErrorMessage.INVALID_DAY_ERROR);
            }
        }

        private static boolean isInvalidRange(int number, int start, int end) {
            return number < start || number > end;
        }

        public static List<Order> validateOrders(String message) {
            List<String> orders = parseStringToList(message, ORDER_SEPARATOR);
            List<Order> orderList = new ArrayList<>();
            for (String order : orders) {
                List<String> orderInfo = parseStringToList(order, ORDER_INFO_SEPARATOR);
                if (orderInfo.size() != 2) {
                    throw CustomException.from(ErrorMessage.INVALID_ORDER_ERROR);
                }
                orderList.add(new Order(
                                Menu.from(orderInfo.get(0)),
                                validateNumber(orderInfo.get(1), ErrorMessage.INVALID_ORDER_ERROR)
                        )
                );
            }
            return orderList;
        }

        private static List<String> parseStringToList(String message, String separator) {
            return Arrays.stream(split(message, separator)).toList();
        }

        private static String[] split(String message, String separator) {
            return message.split(separator, -1);
        }
    }
}
