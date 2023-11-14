package christmas.event.evnets.gift;

import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.enums.menu.MenuItem;
import christmas.event.Gift;
import christmas.utils.EventPeriod;
import java.time.LocalDate;

public class GiftBenefit implements GiftBenefitEvent {
    private final String eventName;
    private final Integer needAmountToGift;
    private final EventPeriod eventPeriod;
    private final MenuItem menuItem;
    private final Integer quantity;

    public GiftBenefit(String eventName, EventPeriod eventPeriod, Integer needAmountToGift, MenuItem menuItem, Integer quantity) {
        this.eventName = eventName;
        this.eventPeriod = eventPeriod;
        this.needAmountToGift = needAmountToGift;
        this.menuItem = menuItem;
        this.quantity = quantity;
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
    public Gift execute(LocalDate reservationDate, Integer totalPriceBeforeDiscount) {

        if (isEventActivate(reservationDate)) {
            MenuItem findMenuItem = isGiftAmountOver(totalPriceBeforeDiscount);
            if (!findMenuItem.isNone()) {
                return new Gift(findMenuItem, quantity);
            }
        }
        return Gift.NO_GIFT();
    }

    public String getEventName() {
        return eventName;
    }
}
