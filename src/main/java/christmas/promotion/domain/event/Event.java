package christmas.promotion.domain.event;

import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;

public interface Event {
    int EVENT_YEAR = 2023;
    int EVENT_MONTH = 12;
    double EVENT_PARTICIPATION_THRESHOLD = 10_000.0;
    Price NON_DISCOUNT_EVENT = Price.zero();
    Price NON_GIFT_EVENT = Price.zero();
    String getEventName();

    boolean isBetweenDates(final DecemberVisitDate date);
}
