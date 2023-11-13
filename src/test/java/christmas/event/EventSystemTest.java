package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.enums.menu.NoMenu;
import christmas.order.Order;
import christmas.order.Orders;
import christmas.systems.event.EventInitializer;
import christmas.systems.event.EventSystem;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventSystemTest {

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
    private final static EventInitializer eventInitializer =new EventInitializer();
    private final static EventSystem eventSystem = new EventSystem(eventInitializer);
    //TODO:추후 추가 테스트 필


    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayGiftEvent() {
        //given
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
        final Integer totalBenefitAmount =
                BASIC_BENEFIT.getAmount() + CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT + WEEK_BENEFIT_CONTAIN_TWO_MAIN;
        //when
        EventBenefit eventBenefit = eventSystem.activateEvent(reservationDate, ordersWithMain);
        Integer totalBenefit = eventBenefit.showTotalDiscount();

        //then
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }
}