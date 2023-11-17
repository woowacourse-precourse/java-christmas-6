package christmas.enums.events.decemberevent;

import christmas.enums.events.Events;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscount;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscountEvent;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;

public enum LinearDiscountEvents implements Events {

    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인",
            new EventPeriod(LocalDate.of(2023, Month.DECEMBER, 1), LocalDate.of(2023, Month.DECEMBER, 25)), 1000, 100,
            new LinearIncreaseDiscount("크리스마스 디데이 할인",
                    new EventPeriod(LocalDate.of(2023, Month.DECEMBER, 1), LocalDate.of(2023, Month.DECEMBER, 25)),
                    1000, 100));
    private final String name;
    private final EventPeriod eventPeriod;
    private final Integer startDiscountBenefit;
    private final Integer increaseBenefitPerDay;
    private final LinearIncreaseDiscountEvent instance;

    LinearDiscountEvents(String name, EventPeriod eventPeriod, Integer startDiscountBenefit,
                         Integer increaseBenefitPerDay, LinearIncreaseDiscountEvent instance) {
        this.name = name;
        this.eventPeriod = eventPeriod;
        this.startDiscountBenefit = startDiscountBenefit;
        this.increaseBenefitPerDay = increaseBenefitPerDay;
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
    public LinearIncreaseDiscountEvent getInstance() {
        return instance;
    }

    public Integer getStartDiscountBenefit() {
        return startDiscountBenefit;
    }

    public Integer getIncreaseBenefitPerDay() {
        return increaseBenefitPerDay;
    }
}
