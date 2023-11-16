package christmas.promotion.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.promotion.exception.OrderMenuException;
import christmas.promotion.exception.VisitDayException;

public class InputView {
    private enum Message {
        VISIT_DAY_IN_DECEMBER("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
        ORDER_DETAILS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        private final String message;

        Message(String message) {
            this.message = message;
        }
    }

    public void requestDecemberVisitDate() {
        System.out.println(Message.VISIT_DAY_IN_DECEMBER.message);
    }

    public void requestOrderDetails() {
        System.out.println(Message.ORDER_DETAILS.message);
    }

    public String readDecemberVisitDay() {
        String decemberVisitData = Console.readLine();
        validateDecemberVisitDate(decemberVisitData);
        return decemberVisitData;
    }

    public String readOrderDetails() {
        String orderDetails = Console.readLine();
        validateOrderDetails(orderDetails);
        return orderDetails;
    }

    private void validateDecemberVisitDate(final String visitDay) {
        int dateMaxLength = 2;
        if (visitDay.isBlank() || visitDay.length() > dateMaxLength) {
            throw new VisitDayException();
        }
    }

    private static void validateOrderDetails(final String orderDetails) {
        int orderDetailsMaxLength = 1000;
        // 주문은 한번에 최대 20개 이므로, 글자가 가장 긴 '크리스마스파스타-1' 글자 수는 10 * 20 = 200이다.
        // 그래서 여유롭게 주문 상세가 1000글자 이상이면 예외를 던진다.
        if(orderDetails.isBlank() || orderDetails.length() > orderDetailsMaxLength) {
            throw new OrderMenuException();
        }
    }
}