package christmas.domain;

import christmas.domain.constants.Menu;
import christmas.domain.constants.MenuCategory;

public class Order {
    private Menu menu;
    private int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public int calculatePrice() {
        return menu.getPrice() * count;
    }

    public boolean isCategory(MenuCategory category) {
        return menu.getCategory().equals(category);
    }
}
