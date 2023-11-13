package christmas.event.evnets.gift;

import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.enums.menu.MenuItem;
import christmas.event.Gift;
import christmas.utils.EventPeriod;
import java.time.LocalDate;

public class AmountToAGiftEvent implements AmountToGiftEvent {
    private final Integer needAmountToGift;
    private final EventPeriod eventPeriod;
    private final MenuItem menuItem;
    private final Integer quantity;

    public AmountToAGiftEvent(EventPeriod eventPeriod, Integer needAmountToGift, MenuItem menuItem, Integer quantity) {
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

    public Boolean isMenuExist(MenuItem menuItem){
        return !menuItem.isNone();
    }
    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public Gift execute(LocalDate reservationDate, Integer totalPriceBeforeDiscount) {
        if (isEventActivate(reservationDate) && menuItem.isNone()) {
            MenuItem findMenuItem = isGiftAmountOver(totalPriceBeforeDiscount);
            return new Gift(findMenuItem,quantity);
        }
        return Gift.NO_GIFT();
    }
}
