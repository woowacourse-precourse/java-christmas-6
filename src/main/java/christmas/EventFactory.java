package christmas;

import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.GiftBenefit;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscount;
import christmas.event.evnets.specialdiscount.SpecialDayDiscount;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.utils.EventPeriod;

public class EventFactory {

    public static LinearIncreaseDiscount createLinearDiscount(String eventName, EventPeriod eventPeriod,
                                                              Integer discountStart, Integer increasePerDay) {
        return new LinearIncreaseDiscount(eventName, eventPeriod, discountStart, increasePerDay);
    }

    public static GiftBenefit createAmountToAGiftEvent(String eventName, EventPeriod eventPeriod, Integer giftCondition,
                                                       MenuItem gift, Integer giftQuantity) {
        return new GiftBenefit(eventName, eventPeriod, giftCondition, gift, giftQuantity);
    }

    public static SpecialDayDiscount createSpecialDayDiscountEvent(String eventName, EventPeriod eventPeriod,
                                                                   Integer discountAmount) {
        return new SpecialDayDiscount(eventName, eventPeriod, discountAmount);
    }

    public static WeekdayDiscount createWeekdayDiscount(String eventName, EventPeriod eventPeriod, MenuItem[] menuItems,
                                                        Integer discountAmount) {
        return new WeekdayDiscount(eventName, eventPeriod, menuItems, discountAmount);
    }

    public static WeekendDiscount createWeekendDiscount(String eventName, EventPeriod eventPeriod, MenuItem[] menuItems,
                                                        Integer discountAmount) {
        return new WeekendDiscount(eventName, eventPeriod, menuItems, discountAmount);
    }
}
