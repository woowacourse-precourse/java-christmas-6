package christmas.order;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.badge.Badge;
import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.events.decemberevent.GiftEvents;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.systems.ordersystem.OrderSystem;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderSystemTest {
    private final static Order oderThreeSteak = new Order(MainMenu.T_BONE_STEAK, 3);
    private final static Orders orderThreeSteak = new Orders(Set.of(oderThreeSteak));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);

    public EventSystem eventSystem() {
        final EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(CHRISTMAS_D_DAY_DISCOUNT.getInstance());
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());
        eventInitializer.amountToGiftEventsAdd(GiftEvents.GIFT_EVENT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKDAY_DISCOUNT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKEND_DISCOUNT.getInstance());

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