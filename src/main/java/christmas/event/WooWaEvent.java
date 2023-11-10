package christmas.event;

import java.time.LocalDate;

public interface WooWaEvent {

     Boolean isEventActivate(LocalDate reservationDate);
     Integer executeEvent(LocalDate reservationDate);
}
