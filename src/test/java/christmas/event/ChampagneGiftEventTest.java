package christmas.event;

import christmas.enums.DessertMenu;
import christmas.enums.MainMenu;
import christmas.order.OrderList;
import christmas.order.OrderMenu;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ChampagneGiftEventTest {

    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 15);
    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK, 2);
    private final static List<OrderMenu> orderMenusOverGiftAmount = List.of(orderMenuWithDessert, orderMenuWithMain);
    private final static List<OrderMenu> orderMenusUnderGiftAmount = List.of(orderMenuWithDessert);
    private final static OrderList orderListOverGiftAmount = new OrderList(orderMenusOverGiftAmount);
    private final static OrderList orderListUnderGiftAmount = new OrderList(orderMenusUnderGiftAmount);
    private final static ChampagneGiftEvent CHAMPAGNE_GIFT_EVENT = new ChampagneGiftEvent(startDate, endDate);


    @Test
    void isGiftAmountOver() {
        //when
        Integer totalOrderAmount = orderListOverGiftAmount.calculateTotalPrice();
        Boolean giftOrNot = CHAMPAGNE_GIFT_EVENT.executeEvent(reservationDate, totalOrderAmount);

        //then
        Assertions.assertThat(giftOrNot).isTrue();
    }

    @Test
    void isGiftAmountUnder() {
        //when
        Integer totalOrderAmount = orderListUnderGiftAmount.calculateTotalPrice();
        Boolean giftOrNot = CHAMPAGNE_GIFT_EVENT.executeEvent(reservationDate, totalOrderAmount);

        //then
        Assertions.assertThat(giftOrNot).isFalse();
    }
}