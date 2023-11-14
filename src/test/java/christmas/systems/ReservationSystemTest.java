package christmas.systems;

import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.EventFactory;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.increasediscount.linearIncreaseDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.systems.eventsystem.EventInitializer;
import christmas.systems.eventsystem.EventSystem;
import christmas.systems.ordersystem.OrderSystem;
import christmas.systems.reservationsystem.ReservationSystem;
import christmas.utils.EventPeriod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservationSystemTest {
    //한달 진행이벤트
    private final static EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
    //특정기간 진행 이벤트
    private final static EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);
    //주말/주간 할인 메뉴
    private final static MenuItem[] weekdayMenus = MainMenu.values();
    private final static MenuItem[] weekendMenus = DessertMenu.values();

    @DisplayName("이벤트 추가에 따라 혜택 내역이 달라진다")
    @Test
    void name() {
        linearIncreaseDiscountEvent linearDiscount = EventFactory.createLinearDiscount(CHRISTMAS_D_DAY_DISCOUNT,
                typicalPeriod, 1000, 100);
        SpecialDayDiscountEvent specialDayDiscountEvent = EventFactory.createSpecialDayDiscountEvent(
                SPECIAL_DISCOUNT, monthPeriod, 1000);
        AmountToAGiftEvent amountToAGiftEvent = EventFactory.createAmountToAGiftEvent(monthPeriod, 120_000, CHAMPAGNE,
                1);
        WeekdayDiscount weekdayDiscount = EventFactory.createWeekdayDiscount(WEEKDAY_DISCOUNT, monthPeriod,
                weekdayMenus, 2023);
        WeekendDiscount weekendDiscount = EventFactory.createWeekendDiscount(WEEKEND_DISCOUNT, monthPeriod,
                weekendMenus, 2023);

        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(linearDiscount);
        eventInitializer.specialDiscountEventAdd(specialDayDiscountEvent);
        eventInitializer.amountToGiftEventsAdd(amountToAGiftEvent);
        eventInitializer.weekDiscountEventAdd(weekdayDiscount);
        eventInitializer.weekDiscountEventAdd(weekendDiscount);

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);

        //reservationSystem.process();
    }
}