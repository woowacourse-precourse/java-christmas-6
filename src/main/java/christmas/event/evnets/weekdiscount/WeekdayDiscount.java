package christmas.event.evnets.weekdiscount;

import christmas.enums.events.Events;
import christmas.enums.menu.MenuItem;
import christmas.event.OneEventResult;
import christmas.order.Orders;
import christmas.utils.EventPeriod;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements WeekDiscountEvent {
    private final String eventName;
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;
    private final MenuItem[] discountMenuItems;

    public WeekdayDiscount(String eventName, EventPeriod eventPeriod, MenuItem[] discountMenuItems,
                           Integer discountAmount) {
        this.eventName = eventName;
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
            return new OneEventResult(eventName, discountBenefit);
        }
        return OneEventResult.NO_EVENT_RESULT();
    }
}
