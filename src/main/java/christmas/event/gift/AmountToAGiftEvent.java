package christmas.event.gift;

import static christmas.enums.menu.None.NONE;

import christmas.enums.menu.None;
import christmas.enums.menu.MenuItem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;

public class AmountToAGiftEvent implements AmountToGiftEvent {
    private final Integer needAmountToGift;
    private final EventPeriod eventPeriod;
    private final MenuItem menuItem;

    public AmountToAGiftEvent(EventPeriod eventPeriod, Integer needAmountToGift, MenuItem menuItem) {
        this.eventPeriod = eventPeriod;
        this.needAmountToGift = needAmountToGift;
        this.menuItem = menuItem;
    }

    public MenuItem isGiftAmountOver(Integer totalOrderAmount) {
        if (totalOrderAmount >= needAmountToGift) {
            return menuItem;
        }
        return NONE;
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
        return NONE;
    }
}
