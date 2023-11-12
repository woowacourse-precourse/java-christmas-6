package christmas.manangers;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.GIFT_CONDITION_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.MINIMUM_REQUIRE_AMOUNT;
import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.END_OF_THE_CHRISTMAS;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.MONTH;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.START_OF_THE_MONTH;
import static christmas.enums.events.decemberevent.DecemberEventPeriod.YEAR;
import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.GIFT_EVENT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.enums.events.NoEvent;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.EventBenefit;
import christmas.event.EventResult;
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
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class WooWaEventManager {
    private final AmountToGiftEvent amountToGiftEvent;
    private final IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent;
    private final SpecialDiscountEvent specialDiscountEvent;
    private final WeekDiscountEvent weekdayDiscountEvent;
    private final WeekDiscountEvent weekendDiscountEvent;

    public WooWaEventManager() {
        final MenuItem[] weekdayMenus = MainMenu.values();
        final MenuItem[] weekendMenus = DessertMenu.values();

        EventPeriod monthPeriod = EventPeriod.createMonthPeriod(YEAR.getDate(), MONTH.getDate());
        EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(YEAR.getDate(), MONTH.getDate(),
                START_OF_THE_MONTH.getDate(), END_OF_THE_CHRISTMAS.getDate());

        increaseEverydayDiscountEvent = new ChristmasDDayDiscount(CHRISTMAS_D_DAY_DISCOUNT, typicalPeriod,
                BASIC_BENEFIT.getAmount(),
                INCREASE_BENEFIT.getAmount());
        amountToGiftEvent = new AmountToAGiftEvent(GIFT_EVENT, monthPeriod, GIFT_CONDITION_BENEFIT.getAmount(), CHAMPAGNE);
        specialDiscountEvent = new SpecialDayDiscountEvent(SPECIAL_DISCOUNT, monthPeriod, BASIC_BENEFIT.getAmount());
        weekdayDiscountEvent = new WeekdayDiscount(WEEKDAY_DISCOUNT, monthPeriod, weekdayMenus, WEEK_BENEFIT.getAmount());
        weekendDiscountEvent = new WeekendDiscount(WEEKEND_DISCOUNT, monthPeriod, weekendMenus, WEEK_BENEFIT.getAmount());
    }


    public EventBenefit activateEvent(LocalDate reservationDate, Orders orders) {
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();

        if (totalPriceBeforeDiscount < MINIMUM_REQUIRE_AMOUNT.getAmount()) {
            return EventBenefit.NO_EVENT_BENEFIT();
        }

        EventResult christmasDDayEventResult = increaseEverydayDiscountEvent.execute(reservationDate);
        EventResult specialDiscountEventResult = specialDiscountEvent.execute(reservationDate);
        EventResult weekdayEventResult = weekdayDiscountEvent.execute(reservationDate, orders);
        EventResult weekendEventResult = weekendDiscountEvent.execute(reservationDate, orders);
        MenuItem gift = amountToGiftEvent.execute(reservationDate, totalPriceBeforeDiscount);

        List<EventResult> eventResults = Stream.of(christmasDDayEventResult, specialDiscountEventResult,
                weekdayEventResult, weekendEventResult).filter(eventResult -> !eventResult.events().equals(NoEvent.NO_EVENT)).toList();

        return new EventBenefit(eventResults, gift);
    }
}
