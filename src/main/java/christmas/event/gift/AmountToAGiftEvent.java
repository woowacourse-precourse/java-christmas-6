package christmas.event.gift;

import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.enums.events.Events;
import christmas.enums.menu.MenuItem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;

public class AmountToAGiftEvent implements AmountToGiftEvent {
    private final Events event;
    private final Integer needAmountToGift;
    private final EventPeriod eventPeriod;
    private final MenuItem menuItem;

    public AmountToAGiftEvent(Events event, EventPeriod eventPeriod, Integer needAmountToGift, MenuItem menuItem) {
        this.event = event;
        this.eventPeriod = eventPeriod;
        this.needAmountToGift = needAmountToGift;
        this.menuItem = menuItem;
    }

    public MenuItem isGiftAmountOver(Integer totalOrderAmount) {
        if (totalOrderAmount >= needAmountToGift) {
            return menuItem;
        }
        return NO_MENU;
    }
    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public MenuItem execute(LocalDate reservationDate, Integer totalPriceBeforeDiscount) {
        if (isEventActivate(reservationDate)) {
            return isGiftAmountOver(totalPriceBeforeDiscount);
        }
        return NO_MENU;
    }
}
