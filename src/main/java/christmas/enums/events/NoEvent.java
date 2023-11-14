package christmas.enums.events;

import christmas.event.evnets.WooWaEvent;
import christmas.utils.EventPeriod;

public enum NoEvent implements Events {
    NO_EVENT("없음", 0);
    private final String name;
    private final Integer amount;

    NoEvent(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public EventPeriod getEventPeriod() {
        return null;
    }

    @Override
    public WooWaEvent getInstance() {
        return null;
    }

    public Integer getAmount() {
        return amount;
    }
}
