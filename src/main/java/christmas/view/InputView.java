package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Menu;
import christmas.utils.Validator;

import java.util.HashMap;
import java.util.Map;

public class InputView {

    private static final String getDateString = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String getOrderString = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public static Integer getDate() {
        System.out.println(getDateString);
        String input = Console.readLine();

        return toDateNumber(input);
    }

    public static Map<Menu, Integer> getOrder() {
        System.out.println(getOrderString);
        String input = Console.readLine();
        Validator.validateOrderForm(input);

        return toOrder(input);
    }

    private static Integer toDateNumber(String str) {
        Validator.isNumber(str);
        Validator.dateOutOfRange(str);
        return Integer.parseInt(str);
    }

    private static Map<Menu, Integer> toOrder(String str) {
        String[] orders = str.split(",");
        Map<Menu, Integer> orderMap = new HashMap<>();
        Integer countSum = 0;

        for(String order : orders) {
            String[] temp = order.split("-");
            Validator.validateOrderMenu(temp[0], orderMap);
            Menu menu = Menu.getEnumByName(temp[0]);
            Integer count = Integer.parseInt(temp[1]);
            Validator.menuCountOutOfRange(count);
            countSum += count;

            orderMap.put(menu, count);
        }
        Validator.menuCountOutOfRange(countSum);

        return orderMap;
    }
}
