package christmas.event.specialdiscount;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;

import christmas.enums.events.Events;
import christmas.event.EventResult;
import christmas.utils.EventPeriod;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDayDiscountEvent implements SpecialDiscountEvent {
    private final Events event;
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;

    public SpecialDayDiscountEvent(Events event, EventPeriod eventPeriod, Integer discountAmount) {
        this.event = event;
        this.eventPeriod = eventPeriod;
        this.discountAmount = discountAmount;
    }

    private Integer calculateDiscount(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SUNDAY || reservationDate.getDayOfMonth() == 25){
            return discountAmount;
        }
        return NO_BENEFIT.getAmount();
    }

    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public EventResult execute(LocalDate reservationDate) {
        if (isEventActivate(reservationDate)) {
            Integer discountBenefit = calculateDiscount(reservationDate);
            return new EventResult(event,discountBenefit);
        }
        return new EventResult(event,NO_BENEFIT.getAmount());
    }
}
