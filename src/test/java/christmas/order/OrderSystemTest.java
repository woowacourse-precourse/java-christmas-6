package christmas.order;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.events.decemberevent.DecemberEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.DecemberEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.*;

import christmas.enums.badge.Badge;
import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.menu.BeverageMenu;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.OneEventResult;
import christmas.manangers.BadgeManager;
import christmas.manangers.WooWaEventManager;
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
    @DisplayName("이벤트별 혜택, 총 혜택, 뱃지, 증정품 여부를 반환한다.")
    @Test
    void orderProcess() {
        //given
        WooWaEventManager wooWaEventManager = new WooWaEventManager();
        BadgeManager badgeManager = new BadgeManager();
        OrderSystem orderSystem = new OrderSystem(wooWaEventManager,badgeManager);
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, orderThreeSteak);

        //when
        Badge badge = receipt.badge();
        Integer totalPriceBeforeDiscount = receipt.totalPriceBeforeDiscount();
        MenuItem gift = receipt.gift();
        List<OneEventResult> oneEventResults = receipt.oneEventResults();

        //then
        assertThat(badge).isEqualTo(BenefitBadge.SANTA);
        assertThat(totalPriceBeforeDiscount).isEqualTo(orderThreeSteak.calculateTotalPrice());
        assertThat(gift).isEqualTo(CHAMPAGNE);
        OneEventResult christmasEvent = new OneEventResult(CHRISTMAS_D_DAY_DISCOUNT.getName(), 1200);
        OneEventResult specialDiscount = new OneEventResult(SPECIAL_DISCOUNT.getName(), BASIC_BENEFIT.getAmount());
        OneEventResult weekdayEvent = new OneEventResult(WEEKDAY_DISCOUNT.getName(), 6069);
        OneEventResult giftEvent = new OneEventResult(CHAMPAGNE.getName(), CHAMPAGNE.getAmount());
        assertThat(oneEventResults).containsExactly(christmasEvent,specialDiscount,weekdayEvent,giftEvent);
    }
}