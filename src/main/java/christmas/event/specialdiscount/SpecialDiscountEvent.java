package christmas.event.specialdiscount;

import christmas.event.WooWaEvent;
import java.time.LocalDate;

public interface SpecialDiscountEvent extends WooWaEvent {
    Integer execute(LocalDate reservationDate);

}
