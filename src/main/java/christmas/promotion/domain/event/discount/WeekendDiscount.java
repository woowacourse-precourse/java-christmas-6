package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.LocalEvent;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.DecemberVisitDate;

import java.time.LocalDate;

public enum WeekendDiscount implements LocalEvent<Price>, DiscountEvent {
    INSTANCE;

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);
    private static final Price DISCOUNT_PRICE = Price.of(2023.0);  // 수정: double 타입으로 변경
    private static final String EVENT_NAME = "주말 할인";

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public Price applyEvent(final DecemberVisitDate date) {
        if (!isPossibleEvent(date)) {
            return NON_DISCOUNT_EVENT;
        }

        return DISCOUNT_PRICE;
    }

    @Override
    public boolean isPossibleEvent(final DecemberVisitDate date) {
        return isBetweenDates(date) && date.isWeekend();
    }

    @Override
    public boolean isBetweenDates(final DecemberVisitDate date) {
        return date.isBetweenDates(EVENT_PERIOD_START, EVENT_PERIOD_END);
    }
}