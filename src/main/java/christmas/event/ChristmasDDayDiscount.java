package christmas.event;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ChristmasDDayDiscount implements WooWaEvent{
    private static final int DISCOUNT_START_AMOUNT = 1000;
    private static final int DISCOUNT_INCREMENT_PER_DAY = 100;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public ChristmasDDayDiscount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private int calculateDiscount(LocalDate reservationDate) {
        int daysElapsed = (int) ChronoUnit.DAYS.between(startDate, reservationDate);
        return DISCOUNT_START_AMOUNT + (daysElapsed * DISCOUNT_INCREMENT_PER_DAY);
    }


    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
       return !(reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate));
    }

    @Override
    public Integer executeEvent(LocalDate reservationDate) {
        if(isEventActivate(reservationDate)){
            return calculateDiscount(reservationDate);
        }
        return 0;
    }
}
