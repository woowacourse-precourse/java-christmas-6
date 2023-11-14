package christmas.domain.view;

import christmas.domain.entity.Order;

public class OutputView {
    public void printMenu(Order order) {

        System.out.println("<주문 메뉴>");

        order.getOrderMap().forEach((key, value) -> System.out.println(key + " " + value + "개"));

    }
}
