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
        int expectedTotalCharge1 = 70_000;
        assertTotalCharge(new OrderList(new String[] {"바비큐립-1", "시저샐러드-2"}), expectedTotalCharge1);
        int expectedTotalCharge2 = 40_000;
        assertTotalCharge(new OrderList(new String[] {"초코케이크-2", "아이스크림-2"}), expectedTotalCharge2);
    }

    private void assertTotalCharge(OrderList orderList, int expectedTotalCharge) {
        int realTotalCharge = orderList.checkOut();
        assertEquals(expectedTotalCharge, realTotalCharge);
    }

    @Test
    void getCountAsTotalCountOfDessertThatOrderListContain() {
        int expectedTotalDessertCount1 = 1;
        assertTotalDessertCount(new OrderList(new String[] {"아이스크림-1"}), expectedTotalDessertCount1);

        int expectedTotalDessertCount2 = 2;
        assertTotalDessertCount(new OrderList(new String[] {"아이스크림-2"}), expectedTotalDessertCount2);


        int expectedTotalDessertCount3 = 0;
        assertTotalDessertCount(new OrderList(new String[] {"제로콜라-1", "양송이수프-3"}), expectedTotalDessertCount3);
    }

    private void assertTotalDessertCount(OrderList orderList, int expectedTotalCount) {
        int realTotalDessertCount = orderList.getTotalDessertCount();
        assertEquals(expectedTotalCount, realTotalDessertCount);
    }
}
