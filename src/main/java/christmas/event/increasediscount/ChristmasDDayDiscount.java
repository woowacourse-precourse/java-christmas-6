package christmas.event.increasediscount;

import christmas.event.EventPeriod;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDDayDiscount implements IncreaseEverydayDiscountEventInterface {
    private final int discountStartAmount;
    private final int discountIncrementPerDay;
    private final EventPeriod eventPeriod;

    public ChristmasDDayDiscount(EventPeriod eventPeriod,int discountStartAmount, int discountIncrementPerDay ) {
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
    public Integer execute(LocalDate reservationDate) {
        if (isEventActivate(reservationDate)) {
            return calculateDiscount(reservationDate);
        }
        return 0;
    }
}
