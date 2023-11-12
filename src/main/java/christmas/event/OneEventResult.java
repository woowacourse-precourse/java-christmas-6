package christmas.event;

import static christmas.enums.events.NoEvent.NO_EVENT;

import christmas.enums.events.Events;

public record OneEventResult(Events events, Integer discountBenefit) {

    public static OneEventResult NO_EVENT_RESULT() {
        return new OneEventResult(NO_EVENT, NO_EVENT.getAmount());
    }

}
