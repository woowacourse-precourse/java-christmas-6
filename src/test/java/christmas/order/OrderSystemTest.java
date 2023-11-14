package christmas.order;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.badge.Badge;
import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.event.evnets.gift.AmountToAGiftEvent;
import christmas.event.evnets.increasediscount.IncreaseDiscountUntilTypicalDay;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekdayDiscount;
import christmas.event.evnets.weekdiscount.WeekendDiscount;
import christmas.EventFactory;
import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.systems.OrderSystem;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderSystemTest {

    private final static Order orderTwoDessert = new Order(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static Order orderOneIceCream = new Order(DessertMenu.ICE_CREAM, 1);
    private final static Order oderThreeSteak = new Order(MainMenu.T_BONE_STEAK, 3);
    private final static Orders ordersOneIceCream = new Orders(Set.of(orderOneIceCream));
    private final static Orders orderThreeSteak = new Orders(Set.of(oderThreeSteak));
    private final static Orders ordersOver120_000 = new Orders(Set.of(orderTwoDessert, oderThreeSteak));
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

    @DisplayName("이벤트별 혜택, 총 혜택, 뱃지, 증정품 여부를 반환한다.")
    @Test
    void orderProcess() {
        //given
        EventSystem eventSystem = eventSystem();
        OrderSystem orderSystem = new OrderSystem(eventSystem);
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, orderThreeSteak);

        //when
        Badge badge = receipt.badge();
        Integer totalPriceBeforeDiscount = receipt.totalPriceBeforeDiscount();
        Gift gift = receipt.gift();
        MenuItem menuItem = gift.menuItem();
        Integer quantity = gift.quantity();
        List<OneEventResult> oneEventResults = receipt.oneEventResults();

        //then
        assertThat(badge).isEqualTo(BenefitBadge.SANTA);
        assertThat(totalPriceBeforeDiscount).isEqualTo(orderThreeSteak.calculateTotalPrice());
        assertThat(menuItem).isEqualTo(CHAMPAGNE);
        assertThat(quantity).isEqualTo(1);
        OneEventResult christmasEvent = new OneEventResult(CHRISTMAS_D_DAY_DISCOUNT.getName(), 1200);
        OneEventResult specialDiscount = new OneEventResult(SPECIAL_DISCOUNT.getName(), BASIC_BENEFIT.getAmount());
        OneEventResult weekdayEvent = new OneEventResult(WEEKDAY_DISCOUNT.getName(), 6069);
        OneEventResult giftEvent = new OneEventResult(CHAMPAGNE.getName(), CHAMPAGNE.getAmount());
        assertThat(oneEventResults).containsExactly(christmasEvent, specialDiscount, weekdayEvent, giftEvent);
    }
}