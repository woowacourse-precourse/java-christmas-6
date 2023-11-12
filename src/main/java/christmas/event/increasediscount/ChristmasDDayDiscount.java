package christmas.event.increasediscount;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;

import christmas.enums.events.Events;
import christmas.event.EventResult;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDDayDiscount implements IncreaseEverydayDiscountEvent {
    private final Events event;
    private final int discountStartAmount;
    private final int discountIncrementPerDay;
    private final EventPeriod eventPeriod;

    public ChristmasDDayDiscount(Events event, EventPeriod eventPeriod, int discountStartAmount, int discountIncrementPerDay ) {
        this.event = event;
        this.discountStartAmount = discountStartAmount;
        this.discountIncrementPerDay = discountIncrementPerDay;
        this.eventPeriod = eventPeriod;
    }

    private int calculateDiscount(LocalDate reservationDate) {
        int daysElapsed = (int) ChronoUnit.DAYS.between(eventPeriod.startDate(), reservationDate);
        return discountStartAmount + (daysElapsed * discountIncrementPerDay);
    }


    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public EventResult execute(LocalDate reservationDate) {
        if (isEventActivate(reservationDate)) {
            int discountBenefit = calculateDiscount(reservationDate);
            return new EventResult(event,discountBenefit);
        }
        return new EventResult(event,NO_BENEFIT.getAmount());
    }
}
