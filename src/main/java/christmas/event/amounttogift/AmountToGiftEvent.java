package christmas.event.amounttogift;

import christmas.event.WooWaEvent;
import christmas.order.Orders;
import java.time.LocalDate;

public interface AmountToGiftEvent extends WooWaEvent {
    String execute(LocalDate reservationDate, Orders orders);

}
