package christmas.event;

import christmas.order.OrderMenu;
import java.time.LocalDate;

public interface WooWaEvent {

    Boolean isEventActivate(LocalDate reservationDate);

    default Integer executeDateToDateEvent(LocalDate reservationDate){
        return null;
    }

    default Integer executePerMenuDiscountEvent(LocalDate reservationDate, OrderMenu orderMenu){
        return null;
    }

    default Boolean executeGiftEvent(LocalDate reservationDate, Integer totalOrderAmount){
        return null;
    }

    default Integer executeDateDiscountEvent(LocalDate reservationDate){
        return null;
    }


}
