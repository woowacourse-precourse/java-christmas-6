package christmas.controller;

import christmas.model.Menu;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class OrderController {

    private OrderService orderService;

    public OrderController() {
        Integer orderDate = InputView.getDate();
        Map<Menu, Integer> orderMap = InputView.getOrder();

        this.orderService = new OrderService(orderDate, orderMap);
    }

    public void order() {
        OutputView.printEventTitle(orderService.getOrderDate());
        OutputView.printOrderMenu(orderService.getOrderMap());
        OutputView.printBeforeDiscountTotalPrice(orderService.calculateTotalPrice());
        orderService.calculateDiscount();
        OutputView.printGift(orderService.getDiscountMap());
        OutputView.printDiscountMap(orderService.getDiscountMap());
        OutputView.printDiscountPrice(orderService.calculateTotalDiscountPrice());
        OutputView.printTotalPayment(orderService.calculateTotalPayment());
        OutputView.printBadge(orderService.calculateBadge());
    }
}
