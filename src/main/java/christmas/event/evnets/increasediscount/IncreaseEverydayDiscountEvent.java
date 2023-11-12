package christmas.event.evnets.increasediscount;

import christmas.event.OneEventResult;
import christmas.event.evnets.WooWaEvent;
import java.time.LocalDate;

public interface IncreaseEverydayDiscountEvent extends WooWaEvent {
    OneEventResult execute(LocalDate reservationDate);
}
