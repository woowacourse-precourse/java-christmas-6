package christmas.systems.event;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.GIFT_CONDITION_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.END_OF_THE_CHRISTMAS;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.MONTH;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.START_OF_THE_MONTH;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.YEAR;
import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.gift.AmountToGiftEvent;
import christmas.event.evnets.increasediscount.IncreaseDiscountUntilTypicalDay;
import christmas.event.evnets.increasediscount.IncreaseEverydayDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.order.Orders;
import christmas.utils.EventPeriod;
import java.time.LocalDate;

public class EventInitializer {
    private final static Integer GIFT_QUANTITY = 1;
    private final AmountToGiftEvent amountToGiftEvent;
    private final IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent;
    private final SpecialDiscountEvent specialDiscountEvent;
    private final WeekDiscountEvent weekdayDiscountEvent;
    private final WeekDiscountEvent weekendDiscountEvent;

    public EventInitializer(AmountToGiftEvent amountToGiftEvent,
                            IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent,
                            SpecialDiscountEvent specialDiscountEvent, WeekDiscountEvent weekdayDiscountEvent,
                            WeekDiscountEvent weekendDiscountEvent) {
        this.amountToGiftEvent = amountToGiftEvent;
        this.increaseEverydayDiscountEvent = increaseEverydayDiscountEvent;
        this.specialDiscountEvent = specialDiscountEvent;
        this.weekdayDiscountEvent = weekdayDiscountEvent;
        this.weekendDiscountEvent = weekendDiscountEvent;
    }

    public OneEventResult christmasDDayDiscount(LocalDate reservationDate){
       return increaseEverydayDiscountEvent.execute(reservationDate);
    }

    public OneEventResult specialDayDiscountEvent(LocalDate reservationDate){
        return specialDiscountEvent.execute(reservationDate);
    }

    public OneEventResult weekdayDiscount(LocalDate reservationDate, Orders orders){
        return weekdayDiscountEvent.execute(reservationDate,orders);
    }

    public OneEventResult weekendDiscount(LocalDate reservationDate, Orders orders){
        return weekendDiscountEvent.execute(reservationDate,orders);
    }

    public Gift amountToAGiftEvent(LocalDate reservationDate, Integer totalPriceBeforeDiscount){
        return amountToGiftEvent.execute(reservationDate,totalPriceBeforeDiscount);
    }
}
