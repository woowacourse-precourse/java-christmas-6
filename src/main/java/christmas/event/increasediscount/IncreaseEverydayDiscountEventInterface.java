package christmas.event.increasediscount;

import christmas.event.WooWaEvent;
import java.time.LocalDate;

public interface IncreaseEverydayDiscountEventInterface extends WooWaEvent {
    Integer execute(LocalDate reservationDate);
}
