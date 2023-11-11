package christmas.event.gift;

import christmas.enums.menu.MenuItem;
import christmas.event.EventPeriod;
import christmas.order.Orders;
import java.time.LocalDate;

public class AmountToAGiftEvent implements AmountToGiftEvent {
    private final static String NONE = "NONE";
    private final Integer needAmountToGift;
    private final EventPeriod eventPeriod;
    private final MenuItem menuItem;

    public AmountToAGiftEvent(EventPeriod eventPeriod, Integer needAmountToGift, MenuItem menuItem) {
        this.eventPeriod = eventPeriod;
        this.needAmountToGift = needAmountToGift;
        this.menuItem = menuItem;
    }

    public String isGiftAmountOver(Integer totalOrderAmount) {
        if (totalOrderAmount >= needAmountToGift) {
            return menuItem.getName();
        }
        return NONE;
    }
    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public String execute(LocalDate reservationDate, Integer totalPriceBeforeDiscount) {
        if (isEventActivate(reservationDate)) {
            return isGiftAmountOver(totalPriceBeforeDiscount);
        }
        return NONE;
    }
}
