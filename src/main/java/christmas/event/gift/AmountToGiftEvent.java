package christmas.event.gift;

import christmas.enums.menu.MenuItem;
import christmas.event.WooWaEvent;
import christmas.order.Orders;
import java.time.LocalDate;

public interface AmountToGiftEvent extends WooWaEvent {
    MenuItem execute(LocalDate reservationDate, Integer totalPriceBeforeDiscount);

}
