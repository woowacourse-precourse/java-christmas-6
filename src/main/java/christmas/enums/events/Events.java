package christmas.enums.events;

import christmas.event.evnets.WooWaEvent;
import christmas.utils.EventPeriod;

public interface Events {
    String getName();

    EventPeriod getEventPeriod();

    WooWaEvent getInstance();
}
