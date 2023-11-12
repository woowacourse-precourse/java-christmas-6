package christmas.event.evnets.specialdiscount;

import christmas.event.EventResult;
import christmas.event.evnets.WooWaEvent;
import java.time.LocalDate;

public interface SpecialDiscountEvent extends WooWaEvent {
    EventResult execute(LocalDate reservationDate);

}
