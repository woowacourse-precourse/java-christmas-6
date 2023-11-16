package christmas.promotion.domain.order;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.domain.menu.EventfulMenu;
import christmas.promotion.vo.Quantity;
import christmas.promotion.domain.visitdate.DecemberVisitDate;

import java.util.Map;

public class OrderMenu {
    private final EventfulMenu menu;
    private final Quantity quantity;
    private final DecemberVisitDate date;

    public OrderMenu(final EventfulMenu menu, final Quantity quantity, final DecemberVisitDate date) {
        this.menu = menu;
        this.quantity = quantity;
        this.date = date;
    }

    public Map<Event, Double> applyDiscount() {
        return menu.applyEvent(date, quantity);
    }

    public double calculateSubtotal() {
        return menu.getPrice() * quantity.quantity();
    }

    public Menu getMenu() {
        return menu.getMenu();
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
