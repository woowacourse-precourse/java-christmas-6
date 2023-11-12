package christmas.event.specialdiscount;

import christmas.enums.events.Events;
import christmas.event.EventResult;
import christmas.event.WooWaEvent;
import java.time.LocalDate;

public interface SpecialDiscountEvent extends WooWaEvent {
    EventResult execute(LocalDate reservationDate);

}
