package christmas.promotion.domain.order;

import christmas.promotion.domain.menu.Beverage;
import christmas.promotion.domain.menu.EventfulMenu;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.MenuBoard;
import christmas.promotion.exception.OrderMenuException;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;
import christmas.promotion.domain.visitdate.DecemberVisitDate;

import java.util.*;

public class Order {
    private static final int ORDER_MENU_MAX_SIZE = 20;

    private final List<OrderMenu> orderMenus;
    private final DecemberVisitDate date;
    private final MenuBoard menuBoard;

    public Order(final Map<String, Integer> order, final DecemberVisitDate decemberVisitDate, final MenuBoard menuBoard) {
        this.menuBoard = menuBoard; // createOrderFromMenuBoard 하기 전, 메뉴판을 동적으로 초기화 해줘야함. 아니면 null
        this.date = decemberVisitDate; // createOrderFromMenuBoard 하기 전, 날짜 먼저 동적으로 최기화 해줘야함. 아니면 null
        this.orderMenus = List.copyOf(createOrderFromMenuBoard(order));
        validate();
    }

    private List<OrderMenu> createOrderFromMenuBoard(final Map<String, Integer> order) {
        return order.entrySet().stream()
                .map(entry -> {
                    EventfulMenu menu = menuBoard.findMenu(entry.getKey());
                    return new OrderMenu(menu, new Quantity(entry.getValue()), this.date);
                })
                .toList();
    }

    public Price calculateOriginalPrice() {
        double total = this.orderMenus.stream()
                .mapToDouble(OrderMenu::calculateSubtotal)
                .sum();
        return Price.of(total);
    }

    private void validate() {
        validateMenuMaxSize();
        validateMenuOnlyBeverage();
    }

    private void validateMenuMaxSize() {
        int totalQuantity = orderMenus.stream()
                .mapToInt(orderMenu -> orderMenu.getQuantity().quantity())
                .sum();

        if (totalQuantity > ORDER_MENU_MAX_SIZE) {
            throw new OrderMenuException();
        }
    }

    private void validateMenuOnlyBeverage() {
        for (OrderMenu orderMenu : orderMenus) {
            if (isNotBeverage(orderMenu.getMenu())) {
                return;
            }
        }

        throw new OrderMenuException();
    }

    private boolean isNotBeverage(final Menu menu) {
        return !(menu instanceof Beverage);
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public DecemberVisitDate getDate() {
        return date;
    }
}
