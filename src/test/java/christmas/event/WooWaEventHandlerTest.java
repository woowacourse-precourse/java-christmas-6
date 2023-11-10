package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.weekdiscount.WeekendDiscount;
import christmas.order.OrderMenu;
import christmas.order.Orders;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WooWaEventHandlerTest {

    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK, 2);
    private final static Orders ordersWithDessert = new Orders(List.of(orderMenuWithDessert));
    private final static Orders ordersWithMain = new Orders(List.of(orderMenuWithMain));
    private final static Orders ordersOver120_000 = new Orders(List.of(orderMenuWithDessert,orderMenuWithMain));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);



    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayGiftEvent() {
        final Integer totalBenefitAmount = 1000 + 1200 + (2023*2) + 25000;
        WooWaEventHandler wooWaEventHandler = new WooWaEventHandler();
        EventBenefit eventBenefit = wooWaEventHandler.activateEvent(reservationDate, ordersOver120_000);
        Integer totalBenefit = eventBenefit.discountBenefit();
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }

    @DisplayName("특별할인, 주중할인(2), 증정이벤트, 크리스마스할인 당첨시")
    @Test
    void whenSpecialChristmasWeekdayEvent() {
        final Integer totalBenefitAmount = 1000 + 1200 + (2023*2);
        WooWaEventHandler wooWaEventHandler = new WooWaEventHandler();
        EventBenefit eventBenefit = wooWaEventHandler.activateEvent(reservationDate, ordersWithMain);
        Integer totalBenefit = eventBenefit.discountBenefit();
        assertThat(totalBenefit).isEqualTo(totalBenefitAmount);

    }
}