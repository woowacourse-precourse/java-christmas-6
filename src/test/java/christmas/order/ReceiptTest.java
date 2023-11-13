package christmas.order;


import christmas.enums.menu.DessertMenu;
import christmas.systems.OrderSystem;
import christmas.systems.event.EventInitializer;
import christmas.systems.event.EventSystem;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceiptTest {
    private final static Order oneIceCream = new Order(DessertMenu.ICE_CREAM, 1);

    private final static Orders ordersOneIceReam = new Orders(Set.of(oneIceCream));

    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);

    @DisplayName("총주문금액 10000원 이하 시 이벤트 무효")
    @Test
    void name() {
        //given
        EventInitializer eventInitializer = new EventInitializer();
        EventSystem eventSystem = new EventSystem(eventInitializer);
        OrderSystem orderSystem = new OrderSystem(eventSystem);

        //when
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, ordersOneIceReam);
        Boolean eligible = receipt.isEligible();

        //then
        Assertions.assertThat(eligible).isFalse();

    }
}