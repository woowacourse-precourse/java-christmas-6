package christmas;

import christmas.controller.OrderController;

public class Application {
    public static void main(String[] args) {
        OrderController controller = new OrderController();
        controller.order();
    }
}
