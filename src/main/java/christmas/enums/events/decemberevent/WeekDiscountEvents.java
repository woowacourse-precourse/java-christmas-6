package christmas.enums.events.decemberevent;

import christmas.enums.events.Events;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;

public enum WeekDiscountEvents implements Events {

    WEEKDAY_DISCOUNT("평일 할인",new EventPeriod(LocalDate.of(2023,Month.DECEMBER,1),LocalDate.of(2023,Month.DECEMBER,31)),
            MainMenu.values(), 2023)
    , WEEKEND_DISCOUNT("주말 할인", new EventPeriod(LocalDate.of(2023,Month.DECEMBER,1),LocalDate.of(2023,Month.DECEMBER,31)),
            DessertMenu.values(), 2023);
    private final String name;
    private final EventPeriod eventPeriod;
    private final MenuItem[] menuItems;
    private final Integer discountBenefit;

    WeekDiscountEvents(String name, EventPeriod eventPeriod, MenuItem[] menuItems, Integer discountBenefit) {
        this.name = name;
        this.eventPeriod = eventPeriod;
        this.menuItems = menuItems;
        this.discountBenefit = discountBenefit;
    }

    @Override
    public String getName() {
        return name;
    }

    public EventPeriod getEventPeriod() {
        return eventPeriod;
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public Integer getDiscountBenefit() {
        return discountBenefit;
    }
}
