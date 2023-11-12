package christmas.event.weekdiscount;

import static christmas.enums.benefit.DiscountBenefit.*;

import christmas.enums.events.Events;
import christmas.enums.menu.MenuItem;
import christmas.event.EventResult;
import christmas.utils.EventPeriod;
import christmas.order.Orders;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements WeekDiscountEvent {
    private final Events event;
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;
    private final MenuItem[] discountMenuItems;

    public WeekdayDiscount(Events event, EventPeriod eventPeriod, MenuItem[] discountMenuItems, Integer discountAmount) {
        this.event = event;
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
    public EventResult execute(LocalDate reservationDate, Orders orders) {
        if (isEventActivate(reservationDate) && isWeekDay(reservationDate)) {
            Integer discountBenefit = calculateDiscount(orders);
            return new EventResult(event,discountBenefit);
        }
        return new EventResult(event,NO_BENEFIT.getAmount());
    }
}
