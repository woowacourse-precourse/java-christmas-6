package christmas.domain.order;

import java.util.Map;

public class Order {

    private final Map<String, Integer> menus;

    public Order(Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Map<String, Integer> getMenus() {
        return this.menus;
    }
}
