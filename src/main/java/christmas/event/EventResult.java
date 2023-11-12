package christmas.event;

import static christmas.enums.events.NoEvent.NO_EVENT;

import christmas.enums.events.Events;

public record EventResult(Events events, Integer discountBenefit) {
    public static EventResult NO_EVENT_RESULT(){
      return new EventResult(NO_EVENT, NO_EVENT.getAmount());
    }
}
