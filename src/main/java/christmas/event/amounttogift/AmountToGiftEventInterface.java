package christmas.event.amounttogift;

import christmas.event.WooWaEvent;
import java.time.LocalDate;

public interface AmountToGiftEventInterface extends WooWaEvent {
    Boolean execute(LocalDate reservationDate, Integer totalOrderAmount);

}
