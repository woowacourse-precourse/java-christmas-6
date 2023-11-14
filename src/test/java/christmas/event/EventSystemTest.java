package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.EventFactory;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.increasediscount.IncreaseDiscountUntilTypicalDay;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.order.Order;
import christmas.order.Orders;
import christmas.systems.event.EventInitializer;
import christmas.systems.event.EventSystem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventSystemTest {
    private final static EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
    private final static EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);
    private final static MenuItem[] weekdayMenus = MainMenu.values();
    private final static MenuItem[] weekendMenus = DessertMenu.values();
    private final static Order orderTwoDessert = new Order(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static Order orderOneIceCream = new Order(DessertMenu.ICE_CREAM, 1);
    private final static Order oderTwoSteak = new Order(MainMenu.T_BONE_STEAK, 2);
    private final static Orders ordersOneIceCream = new Orders(Set.of(orderOneIceCream));
    private final static Orders ordersWithMain = new Orders(Set.of(oderTwoSteak));
    private final static Orders ordersOver120_000 = new Orders(Set.of(orderTwoDessert, oderTwoSteak));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static Integer CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT = (BASIC_BENEFIT.getAmount() + (
            INCREASE_BENEFIT.getAmount() * 2));
    private final static Integer WEEK_BENEFIT_CONTAIN_TWO_MAIN = (WEEK_BENEFIT.getAmount() * 2);
    private final static IncreaseDiscountUntilTypicalDay linearDiscount = EventFactory.createLinearDiscount(
            CHRISTMAS_D_DAY_DISCOUNT,
            typicalPeriod, 1000, 100);
    private final static SpecialDayDiscountEvent specialDayDiscountEvent = EventFactory.createSpecialDayDiscountEvent(
            SPECIAL_DISCOUNT, monthPeriod, 1000);
    private final static AmountToAGiftEvent amountToAGiftEvent = EventFactory.createAmountToAGiftEvent(monthPeriod,
            120_000, CHAMPAGNE,
            1);
    private final static WeekdayDiscount weekdayDiscount = EventFactory.createWeekdayDiscount(WEEKDAY_DISCOUNT,
            monthPeriod,
            weekdayMenus, 2023);
    private final static WeekendDiscount weekendDiscount = EventFactory.createWeekendDiscount(WEEKEND_DISCOUNT,
            monthPeriod,
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

    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayGiftEvent() {
        //given
        final EventSystem eventSystem = eventSystem();
        final Integer totalBenefitAmount =
                BASIC_BENEFIT.getAmount() + CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT + WEEK_BENEFIT_CONTAIN_TWO_MAIN
                        + CHAMPAGNE.getAmount();

        //when
        EventBenefit eventBenefit = eventSystem.activateEvent(reservationDate, ordersOver120_000);
        Integer totalBenefit = eventBenefit.showTotalDiscount();

        //then
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }

    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayEvent() {
        //given
        final EventSystem eventSystem = eventSystem();
        final Integer totalBenefitAmount =
                BASIC_BENEFIT.getAmount() + CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT + WEEK_BENEFIT_CONTAIN_TWO_MAIN;
        //when
        EventBenefit eventBenefit = eventSystem.activateEvent(reservationDate, ordersWithMain);
        Integer totalBenefit = eventBenefit.showTotalDiscount();

        //then
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }
}