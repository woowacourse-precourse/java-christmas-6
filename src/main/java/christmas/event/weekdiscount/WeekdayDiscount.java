package christmas.event.weekdiscount;

import static christmas.enums.benefit.DiscountBenefit.*;

import christmas.enums.menu.MenuItem;
import christmas.event.EventPeriod;
import christmas.order.Orders;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements WeekDiscountEvent {
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;
    private final MenuItem[] discountMenuItems;

    public WeekdayDiscount(EventPeriod eventPeriod, MenuItem[] discountMenuItems, Integer discountAmount) {
        this.eventPeriod = eventPeriod;
        this.discountAmount = discountAmount;
        this.discountMenuItems = discountMenuItems;
    }

    private Integer calculateDiscount(Orders orders) {
            return orders.findEventMenuCount(discountMenuItems) * discountAmount;
    }

    private Boolean isWeekDay(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        return (dayOfWeek != DayOfWeek.FRIDAY && dayOfWeek != DayOfWeek.SATURDAY);
    }

    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public Integer execute(LocalDate reservationDate, Orders orders) {
        if (isEventActivate(reservationDate) && isWeekDay(reservationDate)) {
            return calculateDiscount(orders);
        }
        return NO_BENEFIT.getAmount();
    }
}
