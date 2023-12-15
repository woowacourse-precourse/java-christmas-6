package christmas.view;

import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.view.console.ConsoleWriter;

public class OutputView {
    private static final String ORDER_MENU = "%s %s개";
    private static final String PRICE = "%,d원";

    public void start() {
        ConsoleWriter.printlnMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenuAndPrice(Orders orders) {
        ConsoleWriter.printlnMessage("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        ConsoleWriter.printlnMessage("<주문 메뉴>");
        for (Order order : orders.getOrders()) {
            ConsoleWriter.printlnFormat(ORDER_MENU, order.getMenu().getName(), order.getCount());
        }
        ConsoleWriter.println();

        ConsoleWriter.printlnMessage("<할인 전 총주문 금액>");
        ConsoleWriter.printlnFormat(PRICE, orders.calculateTotalPrice());
        ConsoleWriter.println();
    }
}
