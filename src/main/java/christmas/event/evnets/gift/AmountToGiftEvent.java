package christmas.event.evnets.gift;

import christmas.event.Gift;
import christmas.event.evnets.WooWaEvent;
import java.time.LocalDate;

public interface AmountToGiftEvent extends WooWaEvent {
    Gift execute(LocalDate reservationDate, Integer totalPriceBeforeDiscount);

}
