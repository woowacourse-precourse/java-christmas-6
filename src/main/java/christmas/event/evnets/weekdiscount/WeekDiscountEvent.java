package christmas.event.evnets.weekdiscount;

import christmas.event.EventResult;
import christmas.event.evnets.WooWaEvent;
import christmas.order.Orders;
import java.time.LocalDate;

public interface WeekDiscountEvent extends WooWaEvent {
    EventResult execute(LocalDate reservationDate, Orders orders);
}
