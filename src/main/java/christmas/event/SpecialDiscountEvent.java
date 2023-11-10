package christmas.event;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SpecialDiscountEvent implements WooWaEvent {
    private final static Integer DISCOUNT_AMOUNT = 1000;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SpecialDiscountEvent(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Integer calculateDiscount(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        if(dayOfWeek == DayOfWeek.SUNDAY || reservationDate.getDayOfMonth() == 25){
            return DISCOUNT_AMOUNT;
        }
        return 0;
    }

    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate));
    }

    @Override
    public Integer executeDateDiscountEvent(LocalDate reservationDate) {
        if (isEventActivate(reservationDate)) {
            return calculateDiscount(reservationDate);
        }
        return 0;
    }
}
