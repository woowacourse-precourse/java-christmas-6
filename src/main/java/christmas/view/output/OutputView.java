package christmas.view.output;

import christmas.dto.OrderDto;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String PRINT_ORDER_RESULT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String PRINT_TOTAL_ORDER_MESSAGE = "\n<주문 메뉴>";
    private static final String PRINT_TOTAL_ORDER = "%s %d개\n";
    private static final String PRINT_TOTAL_PRICE_MESSAGE = "\n<할인 전 총주문 금액>\n%s원";
    private static final String PRINT_GIFT_MENU_BENEFIT_MESSAGE = "\n\n<증정 메뉴>\n%s";
    private static final String PRINT_GIFT_MENU_BENEFIT_APPLICABLE = "샴페인 1개";
    private static final String PRINT_GIFT_MENU_BENEFIT_NOT_APPLICABLE = "없음";

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

    public void printGiftMenuBenefit(final OrderDto orderDto) {
        final int totalPrice = orderDto.getTotalPrice();
        final String messageFormat = checkForGiftMenuBenefit(totalPrice);
        System.out.printf(PRINT_GIFT_MENU_BENEFIT_MESSAGE, messageFormat);
    }

    private String checkForGiftMenuBenefit(final int totalPrice) {
        if (totalPrice >= 120_000) {
            return PRINT_GIFT_MENU_BENEFIT_APPLICABLE;
        }
        return PRINT_GIFT_MENU_BENEFIT_NOT_APPLICABLE;
    }
}
