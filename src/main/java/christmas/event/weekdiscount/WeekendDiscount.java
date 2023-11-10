package christmas.event.weekdiscount;

import christmas.enums.MainMenu;
import christmas.enums.MenuItem;
import christmas.event.EventPeriod;
import christmas.order.OrderMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscount implements WeekDiscountEventInterface {
    private final Integer discountAmount;
    private final EventPeriod eventPeriod;

    public WeekendDiscount(EventPeriod eventPeriod, Integer discountAmount) {
        this.eventPeriod = eventPeriod;
        this.discountAmount = discountAmount;
    }

    private int calculateDiscount(OrderMenu orderMenu) {
        if (isMain(orderMenu.getMenuItem())) {
           return orderMenu.getOrderQuantity() * discountAmount;
        }
        return 0;
    }
    private boolean isMain(MenuItem menuItem) {
        return menuItem instanceof MainMenu;
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
    public Integer execute(LocalDate reservationDate, OrderMenu orderMenu) {
        if(isEventActivate(reservationDate) && isWeekend(reservationDate)){
            return calculateDiscount(orderMenu);
        }
        return 0;
    }
}
