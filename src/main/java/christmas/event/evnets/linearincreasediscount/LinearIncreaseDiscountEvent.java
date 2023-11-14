package christmas.event.evnets.linearincreasediscount;

import christmas.event.OneEventResult;
import christmas.event.evnets.WooWaEvent;
import java.time.LocalDate;

public interface LinearIncreaseDiscountEvent extends WooWaEvent {
    OneEventResult execute(LocalDate reservationDate);
}
