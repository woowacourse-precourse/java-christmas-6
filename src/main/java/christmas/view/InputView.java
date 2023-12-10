package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.dto.OrderRequestDto;
import christmas.view.dto.VisitDayRequestDto;

public class InputView {
    private static final String INPUT_VISIT_DAY_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INPUT_ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public VisitDayRequestDto readVisitDay() {
        System.out.println(INPUT_VISIT_DAY_MESSAGE);
        String visitDay = read();

        return new VisitDayRequestDto(visitDay);
    }

    public OrderRequestDto readOrder() {
        System.out.println(INPUT_ORDER_MESSAGE);
        String order = read();

        return new OrderRequestDto(order);
    }

    private String read() {
        return Console.readLine();
    }
}
