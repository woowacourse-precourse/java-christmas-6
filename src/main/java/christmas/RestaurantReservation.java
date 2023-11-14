package christmas;

import static christmas.enums.events.decemberevent.GiftEvents.GIFT_EVENT;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;

import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.systems.ordersystem.OrderSystem;
import christmas.systems.reservationsystem.ReservationProcessor;
import christmas.systems.reservationsystem.ReservationSystem;

public class RestaurantReservation {
    private final ReservationProcessor reservationProcessor;
    private final static String RESTAURANT_NAME = "우테코 식당";
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = 12;

    public RestaurantReservation() {
        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(CHRISTMAS_D_DAY_DISCOUNT.getInstance());
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());
        eventInitializer.amountToGiftEventsAdd(GIFT_EVENT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKDAY_DISCOUNT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKEND_DISCOUNT.getInstance());

        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        ReservationSystem reservationSystem = new ReservationSystem(orderSystem);
        this. reservationProcessor =new ReservationProcessor(reservationSystem);
    }

    public void run(){
        reservationProcessor.process(RESTAURANT_NAME,YEAR,MONTH);
    }
}
