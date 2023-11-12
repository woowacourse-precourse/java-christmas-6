package christmas.event.evnets.weekdiscount;

import static christmas.enums.benefit.DiscountBenefit.*;
import static christmas.enums.events.NoEvent.NO_EVENT;

import christmas.enums.events.Events;
import christmas.enums.menu.MenuItem;
import christmas.event.OneEventResult;
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
    public OneEventResult execute(LocalDate reservationDate, Orders orders) {
        if (isEventActivate(reservationDate) && isWeekDay(reservationDate)) {
            Integer discountBenefit = calculateDiscount(orders);
            return new OneEventResult(event,discountBenefit);
        }
        return new OneEventResult(NO_EVENT,NO_BENEFIT.getAmount());
    }
}
