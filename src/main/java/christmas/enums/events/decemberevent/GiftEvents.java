package christmas.enums.events.decemberevent;

import christmas.enums.events.Events;
import christmas.enums.menu.BeverageMenu;
import christmas.enums.menu.MenuItem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;

public enum GiftEvents implements Events {

    GIFT_EVENT("증정 이벤트", new EventPeriod(LocalDate.of(2023,Month.DECEMBER,1),LocalDate.of(2023,Month.DECEMBER,31)),
            120_000, BeverageMenu.CHAMPAGNE, 1);
    private final String name;
    private final EventPeriod eventPeriod;
    private final Integer giftCondition;
    private final MenuItem menuItem;
    private final Integer quantity;

    GiftEvents(String name, EventPeriod eventPeriod, Integer giftCondition, MenuItem menuItem, Integer quantity) {
        this.name = name;
        this.eventPeriod = eventPeriod;
        this.giftCondition = giftCondition;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    public EventPeriod getEventPeriod() {
        return eventPeriod;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getGiftCondition() {
        return giftCondition;
    }
}
