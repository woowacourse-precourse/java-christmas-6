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
import christmas.event.evnets.increasediscount.ChristmasDDayDiscount;
import christmas.event.evnets.increasediscount.IncreaseEverydayDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.order.Orders;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import org.mockito.internal.matchers.Or;

public class EventInitializer {
    private final static Integer GIFT_QUANTITY = 1;
    private final AmountToGiftEvent amountToGiftEvent;
    private final IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent;
    private final SpecialDiscountEvent specialDiscountEvent;
    private final WeekDiscountEvent weekdayDiscountEvent;
    private final WeekDiscountEvent weekendDiscountEvent;

    public EventInitializer() {
        final MenuItem[] weekdayMenus = MainMenu.values();
        final MenuItem[] weekendMenus = DessertMenu.values();

        EventPeriod monthPeriod = EventPeriod.createMonthPeriod(YEAR.getDate(), MONTH.getDate());
        EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(YEAR.getDate(), MONTH.getDate(),
                START_OF_THE_MONTH.getDate(), END_OF_THE_CHRISTMAS.getDate());

        increaseEverydayDiscountEvent = new ChristmasDDayDiscount(CHRISTMAS_D_DAY_DISCOUNT, typicalPeriod,
                BASIC_BENEFIT.getAmount(),
                INCREASE_BENEFIT.getAmount());
        amountToGiftEvent = new AmountToAGiftEvent(monthPeriod, GIFT_CONDITION_BENEFIT.getAmount(), CHAMPAGNE,
                GIFT_QUANTITY);
        specialDiscountEvent = new SpecialDayDiscountEvent(SPECIAL_DISCOUNT, monthPeriod, BASIC_BENEFIT.getAmount());
        weekdayDiscountEvent = new WeekdayDiscount(WEEKDAY_DISCOUNT, monthPeriod, weekdayMenus,
                WEEK_BENEFIT.getAmount());
        weekendDiscountEvent = new WeekendDiscount(WEEKEND_DISCOUNT, monthPeriod, weekendMenus,
                WEEK_BENEFIT.getAmount());
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
