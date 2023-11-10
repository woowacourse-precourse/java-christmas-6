package christmas.event;

import static christmas.enums.BeverageMenu.CHAMPAGNE;

import christmas.enums.DessertMenu;
import christmas.enums.MainMenu;
import christmas.event.amounttogift.AmountToGiftEvent;
import christmas.order.OrderList;
import christmas.order.OrderMenu;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AmountToGiftEventTest {
    private final static String NONE = "NONE";
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 15);
    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK, 2);
    private final static List<OrderMenu> orderMenusOverGiftAmount = List.of(orderMenuWithDessert, orderMenuWithMain);
    private final static List<OrderMenu> orderMenusUnderGiftAmount = List.of(orderMenuWithDessert);
    private final static OrderList orderListOverGiftAmount = new OrderList(orderMenusOverGiftAmount);
    private final static OrderList orderListUnderGiftAmount = new OrderList(orderMenusUnderGiftAmount);
    private final static AmountToGiftEvent CHAMPAGNE_GIFT_EVENT =
            new AmountToGiftEvent(eventPeriod, 120_000, CHAMPAGNE);


    @Test
    void isGiftAmountOver() {
        //when
        Integer totalOrderAmount = orderListOverGiftAmount.calculateTotalPrice();
        String giftName = CHAMPAGNE_GIFT_EVENT.execute(reservationDate, totalOrderAmount);

        //then
        Assertions.assertThat(giftName).isEqualTo(CHAMPAGNE.getName());
    }

    @Test
    void isGiftAmountUnder() {
        //when
        Integer totalOrderAmount = orderListUnderGiftAmount.calculateTotalPrice();
        String giftName = CHAMPAGNE_GIFT_EVENT.execute(reservationDate, totalOrderAmount);

        //then
        Assertions.assertThat(giftName).isEqualTo(NONE);
    }
}