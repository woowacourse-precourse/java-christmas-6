package christmas;

import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.increasediscount.IncreaseDiscountUntilTypicalDay;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.systems.OrderSystem;
import christmas.systems.ReservationSystem;
import christmas.systems.event.EventInitializer;
import christmas.systems.event.EventSystem;
import christmas.utils.EventPeriod;

public class RestaurantReservation {
    private final ReservationSystem reservationSystem;
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = 12;
    private final static Integer TYPICAL_START_DATE = 1;
    private final static Integer TYPICAL_END_DATE = 25;
    //한달 진행이벤트
    private final static EventPeriod monthPeriod = EventPeriod.createMonthPeriod(YEAR, MONTH);

    //특정기간 진행 이벤트
    private final static EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(YEAR, MONTH, TYPICAL_START_DATE,
            TYPICAL_END_DATE);

    //주말/주간 할인 메뉴
    private final static MenuItem[] weekdayMenus = MainMenu.values();
    private final static MenuItem[] weekendMenus = DessertMenu.values();

    public RestaurantReservation() {
        IncreaseDiscountUntilTypicalDay linearDiscount = EventFactory.createLinearDiscount(CHRISTMAS_D_DAY_DISCOUNT,
                typicalPeriod, 1000, 100);
        SpecialDayDiscountEvent specialDayDiscountEvent = EventFactory.createSpecialDayDiscountEvent(
                SPECIAL_DISCOUNT, monthPeriod, 1000);
        AmountToAGiftEvent amountToAGiftEvent = EventFactory.createAmountToAGiftEvent(monthPeriod, 120_000, CHAMPAGNE,
                1);
        WeekdayDiscount weekdayDiscount = EventFactory.createWeekdayDiscount(WEEKDAY_DISCOUNT, monthPeriod,
                weekdayMenus, 2023);
        WeekendDiscount weekendDiscount = EventFactory.createWeekendDiscount(WEEKEND_DISCOUNT, monthPeriod,
                weekendMenus, 2023);

        EventInitializer eventInitializer = new EventInitializer(amountToAGiftEvent, linearDiscount,
                specialDayDiscountEvent, weekdayDiscount, weekendDiscount);
        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        this.reservationSystem = new ReservationSystem(orderSystem);
    }

    public void run(){
        reservationSystem.process();
    }
}
