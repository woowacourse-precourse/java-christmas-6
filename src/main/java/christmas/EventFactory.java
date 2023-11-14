package christmas;

import christmas.enums.events.Events;
import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.increasediscount.linearIncreaseDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.utils.EventPeriod;

public class EventFactory {

    public static linearIncreaseDiscountEvent createLinearDiscount(Events events, EventPeriod eventPeriod,
                                                                   Integer discountStart, Integer increasePerDay) {
        return new linearIncreaseDiscountEvent(events, eventPeriod, discountStart, increasePerDay);
    }

    public static AmountToAGiftEvent createAmountToAGiftEvent(EventPeriod eventPeriod, Integer giftCondition,
                                                              MenuItem gift, Integer giftQuantity) {
        return new AmountToAGiftEvent(eventPeriod, giftCondition, gift, giftQuantity);
    }

    public static SpecialDayDiscountEvent createSpecialDayDiscountEvent(Events events, EventPeriod eventPeriod,
                                                                        Integer discountAmount) {
        return new SpecialDayDiscountEvent(events, eventPeriod, discountAmount);
    }

    public static WeekdayDiscount createWeekdayDiscount(Events events, EventPeriod eventPeriod, MenuItem[] menuItems,
                                                        Integer discountAmount) {
        return new WeekdayDiscount(events, eventPeriod, menuItems, discountAmount);
    }

    public static WeekendDiscount createWeekendDiscount(Events events, EventPeriod eventPeriod, MenuItem[] menuItems,
                                                        Integer discountAmount) {
        return new WeekendDiscount(events, eventPeriod, menuItems, discountAmount);
    }
}
