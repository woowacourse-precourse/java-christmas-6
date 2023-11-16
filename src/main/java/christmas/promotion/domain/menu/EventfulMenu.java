package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.event.LocalEvent;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;
import christmas.promotion.domain.visitdate.DecemberVisitDate;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventfulMenu {
    private final Menu menu;
    private final List<LocalEvent> localEvents;

    private EventfulMenu(final Menu menu, final List<LocalEvent> localEvents) {
        this.menu = menu;
        this.localEvents = localEvents;
    }

    public static EventfulMenu createMenuItem(final Menu menu) {
        return new EventfulMenu(menu, Collections.emptyList());
    }

    public static EventfulMenu createMenuItemWithDiscount(final Menu menu, final List<LocalEvent> localEvents) {
        return new EventfulMenu(menu, localEvents);
    }

    public Map<Event, Double> applyEvent(final DecemberVisitDate date, final Quantity quantity) {
        Map<Event, Double> localEventBenefit = new LinkedHashMap<>();

        for (LocalEvent localEvent : localEvents) {
            Price discountPrice = (Price) localEvent.applyEvent(date);
            localEventBenefit.put(localEvent, discountPrice.price() * quantity.quantity());
        }

        return localEventBenefit;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getName() {
        return menu.getName();
    }

    public double getPrice() {
        return menu.getPrice();
    }
}
