package christmas.event;

import christmas.order.OrderMenu;
import java.time.LocalDate;

public interface WooWaEvent {

    Boolean isEventActivate(LocalDate reservationDate);

    default Integer executeEvent(LocalDate reservationDate){
        return null;
    }

    default Integer executeEvent(LocalDate reservationDate,OrderMenu orderMenu){
        return null;
    }

    default Boolean executeEvent(LocalDate reservationDate, Integer totalOrderAmount){
        return null;
    }


}
