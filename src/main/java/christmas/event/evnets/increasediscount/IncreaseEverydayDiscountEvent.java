package christmas.event.evnets.increasediscount;

import christmas.event.EventResult;
import christmas.event.evnets.WooWaEvent;
import java.time.LocalDate;

public interface IncreaseEverydayDiscountEvent extends WooWaEvent {
    EventResult execute(LocalDate reservationDate);
}
