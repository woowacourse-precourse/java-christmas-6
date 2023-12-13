package christmas.domain;

import christmas.domain.menu.Category;
import christmas.domain.menu.MenuAndCount;
import java.util.List;

public class Orders {
    private final List<MenuAndCount> orders;

    public Orders(List<MenuAndCount> orders) {
        this.orders = orders;
    }
    public static Orders from(List<String> orders) {
        return new Orders(orders.stream()
                .map(MenuAndCount::from)
                .toList());
    }

    public List<MenuAndCount> getOrders() {
        return orders;
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(MenuAndCount::calcPrice)
                .sum();
    }

    public boolean hasCategoryOf(Category category) {
        return orders.stream()
                .anyMatch(menuAndCount -> menuAndCount.isCategory(category));
    }
    public int getMenuCountOf(Category category) {
        return orders.stream()
                .filter(menuAndCount -> menuAndCount.isCategory(category))
                .mapToInt(MenuAndCount::getCount)
                .sum();
    }
}
