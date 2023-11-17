package christmas.event.evnets.weekdiscount;

import christmas.event.OneEventResult;
import christmas.event.evnets.WooWaEvent;
import christmas.order.Orders;
import java.time.LocalDate;

public interface WeekDiscountEvent extends WooWaEvent {
    OneEventResult execute(LocalDate reservationDate, Orders orders);
}
