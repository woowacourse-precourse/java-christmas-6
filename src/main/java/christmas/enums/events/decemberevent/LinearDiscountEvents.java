package christmas.enums.events.decemberevent;

import christmas.enums.events.Events;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;

public enum LinearDiscountEvents implements Events {

    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인",
            new EventPeriod(LocalDate.of(2023, Month.DECEMBER, 1), LocalDate.of(2023, Month.DECEMBER, 25)), 1000, 100);
    private final String name;
    private final EventPeriod eventPeriod;
    private final Integer startDiscountBenefit;
    private final Integer increaseBenefitPerDay;

    LinearDiscountEvents(String name, EventPeriod eventPeriod, Integer startDiscountBenefit,
                         Integer increaseBenefitPerDay) {
        this.name = name;
        this.eventPeriod = eventPeriod;
        this.startDiscountBenefit = startDiscountBenefit;
        this.increaseBenefitPerDay = increaseBenefitPerDay;
    }

    @Override
    public String getName() {
        return name;
    }

    public EventPeriod getEventPeriod() {
        return eventPeriod;
    }

    public Integer getStartDiscountBenefit() {
        return startDiscountBenefit;
    }

    public Integer getIncreaseBenefitPerDay() {
        return increaseBenefitPerDay;
    }
}
