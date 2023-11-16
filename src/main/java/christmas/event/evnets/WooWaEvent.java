package christmas.event.evnets;

import java.time.LocalDate;

public interface WooWaEvent {

    Boolean isEventActivate(LocalDate reservationDate);

}
