package christmas.event.weekdiscount;

import christmas.enums.DessertMenu;
import christmas.enums.MenuItem;
import christmas.event.EventPeriod;
import christmas.order.OrderMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekdayDiscount implements WeekDiscountEventInterface {
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;

    public WeekdayDiscount(EventPeriod eventPeriod, Integer discountAmount) {
        this.eventPeriod = eventPeriod;
        this.discountAmount = discountAmount;
    }

    private int calculateDiscount(OrderMenu orderMenu) {
        if (isDessert(orderMenu.getMenuItem())) {
           return orderMenu.getOrderQuantity() * discountAmount;
        }
        return 0;
    }
    private boolean isDessert(MenuItem menuItem) {
        return menuItem instanceof DessertMenu;
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
    public Integer execute(LocalDate reservationDate, OrderMenu orderMenu) {
        if(isEventActivate(reservationDate) && isWeekDay(reservationDate)){
            return calculateDiscount(orderMenu);
        }
        return 0;
    }
}
