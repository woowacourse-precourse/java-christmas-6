package christmas.event.specialdiscount;

import christmas.event.EventPeriod;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDayDiscountEvent implements SpecialDiscountEvent {
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;

    public SpecialDayDiscountEvent(EventPeriod eventPeriod, Integer discountAmount) {
        this.eventPeriod = eventPeriod;
        this.discountAmount = discountAmount;
    }

    private Integer calculateDiscount(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SUNDAY || reservationDate.getDayOfMonth() == 25){
            return discountAmount;
        }
        return 0;
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
