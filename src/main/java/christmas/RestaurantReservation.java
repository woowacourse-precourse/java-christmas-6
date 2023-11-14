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
import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.utils.EventPeriod;
import java.time.Month;

public class RestaurantReservation {
    private final ReservationProcessor reservationProcessor;
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = Month.DECEMBER.getValue();
    private final static String RESTAURANT_NAME = "우테코 식당";
    //한달 진행이벤트
    private final static EventPeriod monthPeriod = EventPeriod.createMonthPeriod(YEAR, MONTH);
    //특정기간 진행 이벤트
    private final static EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(YEAR, MONTH, 1, 25);
    //주말/주간 할인 메뉴
    private final static MenuItem[] weekdayMenus = MainMenu.values();
    private final static MenuItem[] weekendMenus = DessertMenu.values();

    public RestaurantReservation() {
        final Integer LINEAR_DISCOUNT_AMOUNT =1000;
        final Integer SPECIAL_DISCOUNT_AMOUNT =1000;
        final Integer INCREASE_PER_DAY = 100;
        final Integer GIFT_CONDITION = 120_000;
        final Integer WEEKDAY_DISCOUNT_AMOUNT = 2023;
        final Integer WEEKEND_DISCOUNT_AMOUNT = 2023;

        LinerIncreaseDiscount linearDiscount = EventFactory.createLinearDiscount(CHRISTMAS_D_DAY_DISCOUNT,
                typicalPeriod, LINEAR_DISCOUNT_AMOUNT, INCREASE_PER_DAY);
        SpecialDayDayDiscount specialDayDiscount = EventFactory.createSpecialDayDiscountEvent(
                SPECIAL_DISCOUNT, monthPeriod, SPECIAL_DISCOUNT_AMOUNT);
        AmountToAGiftEvent amountToAGiftEvent = EventFactory.createAmountToAGiftEvent(monthPeriod, GIFT_CONDITION, CHAMPAGNE,
                1);
        WeekdayDiscount weekdayDiscount = EventFactory.createWeekdayDiscount(WEEKDAY_DISCOUNT, monthPeriod,
                weekdayMenus, WEEKDAY_DISCOUNT_AMOUNT);
        WeekendDiscount weekendDiscount = EventFactory.createWeekendDiscount(WEEKEND_DISCOUNT, monthPeriod,
                weekendMenus, WEEKEND_DISCOUNT_AMOUNT);

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
