package christmas.event.weekdiscount;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;

import christmas.enums.menu.MenuItem;
import christmas.event.EventPeriod;
import christmas.order.Orders;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscount implements WeekDiscountEvent {
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;
    private final MenuItem[] discountMenuItems;

    public WeekendDiscount(EventPeriod eventPeriod, MenuItem[] discountMenuItems, Integer discountAmount) {
        this.eventPeriod = eventPeriod;
        this.discountMenuItems = discountMenuItems;
        this.discountAmount = discountAmount;
    }

    private int calculateDiscount(Orders orders) {
        return orders.findEventMenuCount(discountMenuItems) * discountAmount;

    }

    private Boolean isWeekend(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY);
    }

    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(eventPeriod.startDate()) || reservationDate.isAfter(eventPeriod.endDate()));
    }

    @Override
    public Integer execute(LocalDate reservationDate, Orders orders) {
        if (isEventActivate(reservationDate) && isWeekend(reservationDate)) {
            return calculateDiscount(orders);
        }
        return NO_BENEFIT.getAmount();
    }
}
