package christmas.domain.benefit;

import christmas.domain.order.Order;

public class Benefit {

    private final Order order;

    public Benefit(final Order order) {
        this.order = order;
    }

    public boolean checkForGiftMenu() {
        return order.getTotalPrice() >= 120_000;
    }
}
