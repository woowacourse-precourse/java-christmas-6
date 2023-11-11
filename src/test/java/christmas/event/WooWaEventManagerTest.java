package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.manangers.WooWaEventManager;
import christmas.order.OrderMenu;
import christmas.order.Orders;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WooWaEventManagerTest {

    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK, 2);
    private final static Orders ordersWithDessert = new Orders(List.of(orderMenuWithDessert));
    private final static Orders ordersWithMain = new Orders(List.of(orderMenuWithMain));
    private final static Orders ordersOver120_000 = new Orders(List.of(orderMenuWithDessert, orderMenuWithMain));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static Integer CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT = (BASIC_BENEFIT.getAmount() + (
            INCREASE_BENEFIT.getAmount() * 2));
    private final static Integer WEEK_BENEFIT_CONTAIN_TWO_MAIN = (WEEK_BENEFIT.getAmount() * 2);
    //TODO:추후 추가 테스트 필


    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayGiftEvent() {
        //given
        final Integer totalBenefitAmount =
                BASIC_BENEFIT.getAmount() + CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT + WEEK_BENEFIT_CONTAIN_TWO_MAIN
                        + CHAMPAGNE.getPrice();
        WooWaEventManager wooWaEventManager = new WooWaEventManager();

        //when
        EventBenefit eventBenefit = wooWaEventManager.activateEvent(reservationDate, ordersOver120_000);
        Integer totalBenefit = eventBenefit.discountBenefit();

        //then
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }

    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayEvent() {
        //given
        final Integer totalBenefitAmount =
                BASIC_BENEFIT.getAmount() + CHRIST_MAS_EVENT_AFTER_TWO_DAYS_BENEFIT + WEEK_BENEFIT_CONTAIN_TWO_MAIN;
        WooWaEventManager wooWaEventManager = new WooWaEventManager();

        //when
        EventBenefit eventBenefit = wooWaEventManager.activateEvent(reservationDate, ordersWithMain);
        Integer totalBenefit = eventBenefit.discountBenefit();

        //then
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }
}