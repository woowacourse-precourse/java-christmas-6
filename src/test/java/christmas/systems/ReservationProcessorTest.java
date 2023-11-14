package christmas.systems;

import static christmas.enums.events.decemberevent.DecemberEventPeriod.MONTH;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.YEAR;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.EventFactory;
import christmas.enums.events.decemberevent.GiftEvents;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.GiftBenefit;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscount;
import christmas.event.evnets.specialdiscount.SpecialDayDiscount;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.order.Order;
import christmas.order.Orders;
import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.systems.ordersystem.OrderSystem;
import christmas.systems.reservationsystem.ReservationProcessor;
import christmas.systems.reservationsystem.ReservationSystem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ReservationProcessorTest {
    private final static EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
    private final static EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);
    private final static MenuItem[] weekdayMenus = MainMenu.values();
    private final static MenuItem[] weekendMenus = DessertMenu.values();
    private final static Order orderTwoDessert = new Order(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static Order orderOneIceCream = new Order(DessertMenu.ICE_CREAM, 1);
    private final static Order oderTwoSteak = new Order(MainMenu.T_BONE_STEAK, 2);
    private final static Orders dessertAndSteakOrders = new Orders(Set.of(orderTwoDessert, oderTwoSteak));
    private final static Orders iceCreamOrders = new Orders(Set.of(orderOneIceCream));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static String RESTAURANT_NAME = "우테코 식당";
    ReservationSystem setALLEvent() {
        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(CHRISTMAS_D_DAY_DISCOUNT.getInstance());
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());
        eventInitializer.amountToGiftEventsAdd(GiftEvents.GIFT_EVENT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKDAY_DISCOUNT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKEND_DISCOUNT.getInstance());

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        return new ReservationSystem(orderSystem);
    }

    @Test
    void name() {
        ReservationSystem reservationSystem = this.setALLEvent();
        ReservationProcessor reservationProcessor = new ReservationProcessor(reservationSystem);
       // reservationProcessor.process(RESTAURANT_NAME, YEAR.getValue(),MONTH.getValue());
    }
}