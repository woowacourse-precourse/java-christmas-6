package christmas.domain;

import christmas.domain.constants.MenuCategory;
import java.util.List;

public class Orders {
    private List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public int getCountOfCategory(MenuCategory category) {
        return (int) orders.stream()
                .filter(order -> order.isCategory(category))
                .count();
    }

    public int calculateTotalPrice() {
        return orders.stream()
                .mapToInt(order -> order.calculatePrice())
                .sum();
    }
}
