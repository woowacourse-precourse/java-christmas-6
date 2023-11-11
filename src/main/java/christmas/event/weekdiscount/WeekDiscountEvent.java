package christmas.event.weekdiscount;

import christmas.event.WooWaEvent;
import christmas.order.Orders;
import java.time.LocalDate;

public interface WeekDiscountEvent extends WooWaEvent {
    Integer execute(LocalDate reservationDate, Orders orders);
}
