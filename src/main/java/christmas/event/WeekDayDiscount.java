package christmas.event;

import christmas.enums.DessertMenu;
import christmas.enums.MenuItem;
import christmas.order.OrderMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekDayDiscount implements WooWaEvent {
    private final static Integer DISCOUNT_AMOUNT = 2023;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public WeekDayDiscount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private int calculateDiscount(OrderMenu orderMenu) {
        if (isDessert(orderMenu.getMenuItem())) {
           return orderMenu.getOrderQuantity() * DISCOUNT_AMOUNT;
        }
        return 0;
    }
    private boolean isDessert(MenuItem menuItem) {
        return menuItem instanceof DessertMenu;
    }

    private Boolean isWeekDay(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        return (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY);
    }

    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate));
    }

    @Override
    public Integer executePerMenuDiscountEvent(LocalDate reservationDate, OrderMenu orderMenu) {
        if(isEventActivate(reservationDate) && isWeekDay(reservationDate)){
            return calculateDiscount(orderMenu);
        }
        return 0;
    }
}
