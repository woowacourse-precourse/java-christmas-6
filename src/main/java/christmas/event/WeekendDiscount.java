package christmas.event;

import christmas.enums.DessertMenu;
import christmas.enums.MainMenu;
import christmas.enums.MenuItem;
import christmas.order.OrderMenu;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekendDiscount implements WooWaEvent {
    private final static Integer DISCOUNT_AMOUNT = 2023;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public WeekendDiscount(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int calculateDiscount(OrderMenu orderMenu) {
        if (isMain(orderMenu.getMenuItem())) {
           return orderMenu.getOrderQuantity() * DISCOUNT_AMOUNT;
        }
        return 0;
    }
    private boolean isMain(MenuItem menuItem) {
        return menuItem instanceof MainMenu;
    }

    private Boolean isWeekend(LocalDate reservationDate) {
        DayOfWeek dayOfWeek = reservationDate.getDayOfWeek();
        return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
    }

    @Override
    public Boolean isEventActivate(LocalDate reservationDate) {
        return !(reservationDate.isBefore(startDate) || reservationDate.isAfter(endDate));
    }

    @Override
    public Integer executeEvent(LocalDate reservationDate,OrderMenu orderMenu) {
        if(isEventActivate(reservationDate) && isWeekend(reservationDate)){
            return calculateDiscount(orderMenu);
        }
        return 0;
    }
}
