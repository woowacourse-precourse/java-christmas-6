package christmas.domain;

import christmas.domain.menu.MenuAndCount;
import java.util.List;

public class Orders {
    private final List<MenuAndCount> orders;

    public Orders(List<String> orders) {
        this.orders = orders.stream()
                .map(MenuAndCount::from)
                .toList();
    }

    public List<MenuAndCount> getOrders() {
        return orders;
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(MenuAndCount::calcPrice)
                .sum();
    }
}
