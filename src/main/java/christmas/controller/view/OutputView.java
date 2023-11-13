package christmas.controller.view;

import christmas.enrtity.Order;

public class OutputView {
    public void printMenu(Order order) {

        System.out.println("<주문 메뉴>");

        order.getOrderMap().forEach((key, value) -> System.out.println(key + " " + value + "개"));


    }
}
