package christmas.event.weekdiscount;

import christmas.event.WooWaEvent;
import christmas.order.OrderMenu;
import java.time.LocalDate;

public interface WeekDiscountEventInterface extends WooWaEvent {
    Integer execute(LocalDate reservationDate, OrderMenu orderMenu);
}
