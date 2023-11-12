package christmas.event.increasediscount;

import christmas.event.EventResult;
import christmas.event.WooWaEvent;
import java.time.LocalDate;

public interface IncreaseEverydayDiscountEvent extends WooWaEvent {
    EventResult execute(LocalDate reservationDate);
}
