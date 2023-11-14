package christmas.enums.events.decemberevent;

import christmas.enums.events.Events;
import christmas.event.evnets.WooWaEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscount;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;

public enum SpecialDiscount implements Events {
    SPECIAL_DISCOUNT("특별 할인",new EventPeriod(LocalDate.of(2023,Month.DECEMBER,1),LocalDate.of(2023,Month.DECEMBER,31)),
            1000, new SpecialDayDiscount("특별 할인",new EventPeriod(LocalDate.of(2023,Month.DECEMBER,1),LocalDate.of(2023,Month.DECEMBER,31)),
            1000));
    private final String name;
    private final EventPeriod eventPeriod;
    private final Integer discountBenefit;
    private final SpecialDayDiscountEvent instance;

    SpecialDiscount(String name, EventPeriod eventPeriod, Integer discountBenefit, SpecialDayDiscountEvent instance) {
        this.name = name;
        this.eventPeriod = eventPeriod;
        this.discountBenefit = discountBenefit;
        this.instance = instance;
    }

    @Override
    public String getName() {
        return name;
    }

    public EventPeriod getEventPeriod() {
        return eventPeriod;
    }

    @Override
    public SpecialDayDiscountEvent getInstance() {
        return instance;
    }

    public Integer getDiscountBenefit() {
        return discountBenefit;
    }
}
