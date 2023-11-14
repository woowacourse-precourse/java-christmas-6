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
import christmas.event.evnets.increasediscount.LinerIncreaseDiscount;
import christmas.event.evnets.specialdiscount.SpecialDayDayDiscount;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.systems.ordersystem.OrderSystem;
import christmas.systems.reservationsystem.ReservationProcessor;
import christmas.systems.reservationsystem.ReservationSystem;
import christmas.systems.eventsystem.EventInitializer;
import christmas.systems.eventsystem.EventSystem;
import christmas.utils.EventPeriod;
import java.time.Month;

public class RestaurantReservation {
    private final ReservationProcessor reservationProcessor;
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = Month.DECEMBER.getValue();
    private final static String RESTAURANT_NAME = "우테코 식당";
    //한달 진행이벤트
    private final static EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
    //특정기간 진행 이벤트
    private final static EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);
    //주말/주간 할인 메뉴
    private final static MenuItem[] weekdayMenus = MainMenu.values();
    private final static MenuItem[] weekendMenus = DessertMenu.values();

    public RestaurantReservation() {
        LinerIncreaseDiscount linearDiscount = EventFactory.createLinearDiscount(CHRISTMAS_D_DAY_DISCOUNT,
                typicalPeriod, 1000, 100);
        SpecialDayDayDiscount specialDayDiscount = EventFactory.createSpecialDayDiscountEvent(
                SPECIAL_DISCOUNT, monthPeriod, 1000);
        AmountToAGiftEvent amountToAGiftEvent = EventFactory.createAmountToAGiftEvent(monthPeriod, 120_000, CHAMPAGNE,
                1);
        WeekdayDiscount weekdayDiscount = EventFactory.createWeekdayDiscount(WEEKDAY_DISCOUNT, monthPeriod,
                weekdayMenus, 2023);
        WeekendDiscount weekendDiscount = EventFactory.createWeekendDiscount(WEEKEND_DISCOUNT, monthPeriod,
                weekendMenus, 2023);

        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(linearDiscount);
        eventInitializer.specialDiscountEventAdd(specialDayDiscount);
        eventInitializer.amountToGiftEventsAdd(amountToAGiftEvent);
        eventInitializer.weekDiscountEventAdd(weekdayDiscount);
        eventInitializer.weekDiscountEventAdd(weekendDiscount);

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);
        this. reservationProcessor =new ReservationProcessor(reservationSystem);
    }

    public void run(){
        reservationProcessor.process(RESTAURANT_NAME,YEAR,MONTH);
    }
}
