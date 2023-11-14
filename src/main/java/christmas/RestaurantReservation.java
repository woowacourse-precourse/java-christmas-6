package christmas;

import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.GiftEvents.GIFT_EVENT;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;

import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscount;
import christmas.event.evnets.specialdiscount.SpecialDayDayDiscount;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
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
        LinearIncreaseDiscount linearDiscount = EventFactory.createLinearDiscount(CHRISTMAS_D_DAY_DISCOUNT,
                CHRISTMAS_D_DAY_DISCOUNT.getEventPeriod(), CHRISTMAS_D_DAY_DISCOUNT.getStartDiscountBenefit(),
                CHRISTMAS_D_DAY_DISCOUNT.getIncreaseBenefitPerDay());
        SpecialDayDayDiscount specialDayDiscount = EventFactory.createSpecialDayDiscountEvent(
                SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getEventPeriod(), SPECIAL_DISCOUNT.getDiscountBenefit());
        AmountToAGiftEvent amountToAGiftEvent = EventFactory.createAmountToAGiftEvent(GIFT_EVENT.getEventPeriod(), GIFT_EVENT.getGiftCondition(),
                GIFT_EVENT.getMenuItem(), GIFT_EVENT.getQuantity());
        WeekdayDiscount weekdayDiscount = EventFactory.createWeekdayDiscount(WEEKDAY_DISCOUNT, WEEKDAY_DISCOUNT.getEventPeriod(),
                WEEKDAY_DISCOUNT.getMenuItems(), WEEKDAY_DISCOUNT.getDiscountBenefit());
        WeekendDiscount weekendDiscount = EventFactory.createWeekendDiscount(WEEKEND_DISCOUNT, WEEKEND_DISCOUNT.getEventPeriod(),
                WEEKEND_DISCOUNT.getMenuItems(), WEEKEND_DISCOUNT.getDiscountBenefit());

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
