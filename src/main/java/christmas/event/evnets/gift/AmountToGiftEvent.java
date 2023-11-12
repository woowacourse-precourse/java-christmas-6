package christmas.event.evnets.gift;

import christmas.enums.menu.MenuItem;
import christmas.event.evnets.WooWaEvent;
import java.time.LocalDate;

public interface AmountToGiftEvent extends WooWaEvent {
    MenuItem execute(LocalDate reservationDate, Integer totalPriceBeforeDiscount);

}
