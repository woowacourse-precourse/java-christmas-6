package christmas.event.evnets;

import christmas.event.OneEventResult;
import christmas.order.Orders;
import java.time.LocalDate;

public interface WooWaEvent {

    Boolean isEventActivate(LocalDate reservationDate);

}
