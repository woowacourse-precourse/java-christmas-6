package christmas.view.output;

import christmas.dto.OrderDto;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String PRINT_ORDER_RESULT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String PRINT_TOTAL_ORDER_MESSAGE = "\n<주문 메뉴>";
    private static final String PRINT_TOTAL_ORDER = "%s %d개\n";

    private static final String PRINT_TOTAL_PRICE_MESSAGE = "\n<할인 전 총주문 금액>\n%s원";

    public void printOrderResult(final int date) {
        System.out.printf(PRINT_ORDER_RESULT_MESSAGE, date);
    }

    public void printTotalMenu(final OrderDto orderDto) {
        final Map<String, Integer> order = orderDto.getOrder();
        System.out.println(PRINT_TOTAL_ORDER_MESSAGE);
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            final String menu = entry.getKey();
            final int quantity = entry.getValue();
            System.out.printf(PRINT_TOTAL_ORDER, menu,  quantity);
        }
    }

    public void printTotalPrice(final OrderDto orderDto) {
        DecimalFormat formatter = new DecimalFormat("###,###,###,###");
        final int totalPrice = orderDto.getTotalPrice();
        System.out.printf(PRINT_TOTAL_PRICE_MESSAGE, formatter.format(totalPrice));
    }
}
