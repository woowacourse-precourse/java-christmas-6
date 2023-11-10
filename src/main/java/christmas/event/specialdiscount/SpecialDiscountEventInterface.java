package christmas.event.specialdiscount;

import christmas.event.WooWaEvent;
import java.time.LocalDate;

public interface SpecialDiscountEventInterface extends WooWaEvent {
    Integer execute(LocalDate reservationDate);

}
