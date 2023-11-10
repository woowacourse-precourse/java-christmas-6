package christmas.event.amounttogift;

import christmas.enums.MenuItem;
import christmas.event.EventPeriod;
import java.time.LocalDate;

public class AmountToGiftEvent implements AmountToGiftEventInterface {
    private final static String NONE = "NONE";
    private final Integer defaultGiftAmount;//TODO: 명칭변경
    private final EventPeriod eventPeriod;
    private final MenuItem menuItem;

    public AmountToGiftEvent(EventPeriod eventPeriod, Integer defaultGiftAmount, MenuItem menuItem) {
        this.eventPeriod = eventPeriod;
        this.defaultGiftAmount = defaultGiftAmount;
        this.menuItem = menuItem;
    }

    public String isGiftAmountOver(Integer totalOrderAmount) {
        if (totalOrderAmount > defaultGiftAmount) {
            return menuItem.getName();
        }
        return NONE;
    }
    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public String execute(LocalDate reservationDate, Integer totalOrderAmount) {
        if (isEventActivate(reservationDate)) {
            return isGiftAmountOver(totalOrderAmount);
        }
        return NONE;
    }
}
