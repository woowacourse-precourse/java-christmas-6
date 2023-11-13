package christmas.event;

import static christmas.enums.events.NoEvent.NO_EVENT;

import christmas.enums.events.Events;

public record OneEventResult(String eventName, Integer discountBenefit) {

    public static OneEventResult NO_EVENT_RESULT() {
        return new OneEventResult(NO_EVENT.getName(), NO_EVENT.getAmount());
    }

    public Boolean isNone(){
       return this.eventName.equals(NO_EVENT.getName());
    }

}
