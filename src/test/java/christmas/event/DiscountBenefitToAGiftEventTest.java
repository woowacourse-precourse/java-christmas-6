package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.GIFT_CONDITION_BENEFIT;
import static christmas.enums.events.decemberevent.DecemberEvents.GIFT_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.gift.AmountToAGiftEvent;
import christmas.order.Order;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountBenefitToAGiftEventTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 15);
    private final static Order ORDER_WITH_DESSERT = new Order(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static Order ORDER_WITH_MAIN = new Order(MainMenu.T_BONE_STEAK, 2);
    private final static List<Order> ORDER_MENUS_OVER_GIFT_AMOUNT = List.of(ORDER_WITH_DESSERT, ORDER_WITH_MAIN);
    private final static List<Order> ORDER_MENUS_UNDER_GIFT_AMOUNT = List.of(ORDER_WITH_DESSERT);
    private final static Integer OVER_120_000 = 120_000;
    private final static Integer UNDER_120_000 = 110_000;
    private final static AmountToAGiftEvent CHAMPAGNE_GIFT_EVENT =
            new AmountToAGiftEvent(GIFT_EVENT, eventPeriod, GIFT_CONDITION_BENEFIT.getAmount(), CHAMPAGNE);


    @DisplayName("120,000원 이상이면 증정품을 반환한다.")
    @Test
    void isGiftAmountOver() {
        //when
        MenuItem gift = CHAMPAGNE_GIFT_EVENT.execute(reservationDate, OVER_120_000);

        //then
        Assertions.assertThat(gift).isEqualTo(CHAMPAGNE);
    }

    @DisplayName("120,000원 미만이면 증정품을 반환하지 않는다.")
    @Test
    void isGiftAmountUnder() {
        //when
        MenuItem gift = CHAMPAGNE_GIFT_EVENT.execute(reservationDate,UNDER_120_000 );

        //then
        Assertions.assertThat(gift).isEqualTo(NO_MENU);
    }
}