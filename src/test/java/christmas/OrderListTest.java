package christmas;

import christmas.domain.OrderList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderListTest {
    @Test
    void addOrderWhichIsAlreadyInOrderList() {
        assertThatThrownBy(() -> new OrderList(new String[] {"해산물파스타-2", "해산물파스타-1"}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createTwoOrderListsWhichEachOfTotalChargeIsSeventyThousandAndFourtyThousand() {
        OrderList orderList1 = new OrderList(new String[] {"바비큐립-1", "시저샐러드-2"});
        int resultTotalCharge1 = orderList1.checkOut();

        int expectedTotalCharge1 = 70_000;

        assertEquals(expectedTotalCharge1, resultTotalCharge1);

        OrderList orderList2 = new OrderList(new String[] {"초코케이크-2", "아이스크림-2"});
        int resultTotalCharge2 = orderList2.checkOut();

        int expectedTotalCharge2 = 40_000;

        assertEquals(expectedTotalCharge2, resultTotalCharge2);
    }
}
