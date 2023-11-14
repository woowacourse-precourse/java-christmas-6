package christmas.order;


import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.increasediscount.IncreaseDiscountUntilTypicalDay;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.systems.OrderSystem;
import christmas.EventFactory;
import christmas.systems.event.EventInitializer;
import christmas.systems.event.EventSystem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceiptTest {
    private final static Order oneIceCream = new Order(DessertMenu.ICE_CREAM, 1);

    private final static Orders ordersOneIceReam = new Orders(Set.of(oneIceCream));

    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
    private final static EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);
    private final static MenuItem[] weekdayMenus = MainMenu.values();
    private final static MenuItem[] weekendMenus = DessertMenu.values();
    private final static IncreaseDiscountUntilTypicalDay linearDiscount = EventFactory.createLinearDiscount(CHRISTMAS_D_DAY_DISCOUNT,
            typicalPeriod, 1000, 100);
    private final static SpecialDayDiscountEvent specialDayDiscountEvent = EventFactory.createSpecialDayDiscountEvent(
            SPECIAL_DISCOUNT, monthPeriod, 1000);
    private final static AmountToAGiftEvent amountToAGiftEvent = EventFactory.createAmountToAGiftEvent(monthPeriod, 120_000, CHAMPAGNE,
            1);
    private final static WeekdayDiscount weekdayDiscount = EventFactory.createWeekdayDiscount(WEEKDAY_DISCOUNT, monthPeriod,
            weekdayMenus, 2023);
    private final static WeekendDiscount weekendDiscount = EventFactory.createWeekendDiscount(WEEKEND_DISCOUNT, monthPeriod,
            weekendMenus, 2023);


    public EventSystem eventSystem() {
        final EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(linearDiscount);
        eventInitializer.specialDiscountEventAdd(specialDayDiscountEvent);
        eventInitializer.amountToGiftEventsAdd(amountToAGiftEvent);
        eventInitializer.weekDiscountEventAdd(weekdayDiscount);
        eventInitializer.weekDiscountEventAdd(weekendDiscount);

        return new EventSystem(eventInitializer);
    }

    @DisplayName("총주문금액 10000원 이하 시 이벤트 무효")
    @Test
    void name() {
        //given
        EventSystem eventSystem = eventSystem();
        OrderSystem orderSystem = new OrderSystem(eventSystem);

        //when
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, ordersOneIceReam);
        Boolean eligible = receipt.isEligible();

        //then
        Assertions.assertThat(eligible).isFalse();

    }
}