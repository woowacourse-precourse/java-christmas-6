package christmas.model;

import java.util.List;

public class Order {
    private static Order order;
    private List<Item> items;
    private int date;

    private Order(List<Item> items, int date) {
        this.items = items;
        this.date = date;
    }

    public static Order getInstance(List<Item> items, int date) {
        if (order == null) {
            order = new Order(items, date);
        }
        return order;
    }
}
