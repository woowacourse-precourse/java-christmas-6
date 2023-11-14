package christmas.enums.events.decemberevent;

import christmas.enums.events.Events;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;

public enum SpecialDiscount implements Events {
    SPECIAL_DISCOUNT("특별 할인",new EventPeriod(LocalDate.of(2023,Month.DECEMBER,1),LocalDate.of(2023,Month.DECEMBER,31)),
            1000);
    private final String name;
    private final EventPeriod eventPeriod;
    private final Integer discountBenefit;

    SpecialDiscount(String name, EventPeriod eventPeriod, Integer discountBenefit) {
        this.name = name;
        this.eventPeriod = eventPeriod;
        this.discountBenefit = discountBenefit;
    }

    @Override
    public String getName() {
        return name;
    }

    public EventPeriod getEventPeriod() {
        return eventPeriod;
    }

    public Integer getDiscountBenefit() {
        return discountBenefit;
    }
}
