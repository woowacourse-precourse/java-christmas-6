package christmas.event;

import christmas.order.OrderMenu;
import java.time.LocalDate;

public interface WooWaEvent {

    Boolean isEventActivate(LocalDate reservationDate);

}
