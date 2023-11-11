package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.GIFT_CONDITION_BENEFIT;
import static christmas.enums.benefit.GiftBenefit.*;
import static christmas.enums.benefit.GiftBenefit.NONE;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.enums.benefit.GiftBenefit;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.event.amounttogift.AmountToAGiftEvent;
import christmas.order.Orders;
import christmas.order.OrderMenu;
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
    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK, 2);
    private final static List<OrderMenu> orderMenusOverGiftAmount = List.of(orderMenuWithDessert, orderMenuWithMain);
    private final static List<OrderMenu> orderMenusUnderGiftAmount = List.of(orderMenuWithDessert);
    private final static Orders ORDERS_OVER_GIFT_AMOUNT = new Orders(orderMenusOverGiftAmount);
    private final static Orders ORDERS_UNDER_GIFT_AMOUNT = new Orders(orderMenusUnderGiftAmount);
    private final static AmountToAGiftEvent CHAMPAGNE_GIFT_EVENT =
            new AmountToAGiftEvent(eventPeriod, GIFT_CONDITION_BENEFIT.getAmount(), CHAMPAGNE);


    @DisplayName("120,000원 이상이면 증정품을 반환한다.")
    @Test
    void isGiftAmountOver() {
        //when
        String giftName = CHAMPAGNE_GIFT_EVENT.execute(reservationDate, ORDERS_OVER_GIFT_AMOUNT);

        //then
        Assertions.assertThat(giftName).isEqualTo(CHAMPAGNE.getName());
    }

    @DisplayName("120,000원 미만이면 증정품을 반환하지 않는다.")
    @Test
    void isGiftAmountUnder() {
        //when
        String giftName = CHAMPAGNE_GIFT_EVENT.execute(reservationDate, ORDERS_UNDER_GIFT_AMOUNT);

        //then
        Assertions.assertThat(giftName).isEqualTo(NONE.name());
    }
}