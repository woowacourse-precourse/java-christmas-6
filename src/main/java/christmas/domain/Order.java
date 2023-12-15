package christmas.domain;

import christmas.domain.constants.Menu;

public class Order {
    private Menu menu;
    private int count;

    public Order(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }
}
