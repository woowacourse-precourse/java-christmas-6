package christmas.event.evnets.specialdiscount;

import christmas.event.OneEventResult;
import christmas.event.evnets.WooWaEvent;
import java.time.LocalDate;

public interface SpecialDiscountEvent extends WooWaEvent {
    OneEventResult execute(LocalDate reservationDate);

}
