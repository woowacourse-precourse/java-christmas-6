package christmas;

import christmas.domain.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest {
    @Test
    void createOrderOfWhichQuantityIsUnderOne() {
        assertThatThrownBy(() -> new Order("샴페인-0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createOrderOfWhichFoodIsNotOnMenu() {
        assertThatThrownBy(() -> new Order("볶음밥-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createOrderOfWhichInputIsSplitByComma() {
        assertThatThrownBy(() -> new Order("샴페인,1"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Order("샴페인-1-샴페인"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void createOrderOfWhichQuantityInputIsWrong() {
        assertThatThrownBy(() -> new Order("샴페인-한개"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void compareFoodWhichIsSame() {
        boolean expectation = true;
        assertFoodIsInOrderList(new Order("해산물파스타-1"), new Order("해산물파스타-2"), expectation);
    }

    private void assertFoodIsInOrderList(Order orderInList, Order newOrder, boolean expectation) {
        boolean realResult = orderInList.isSame(newOrder);
        assertEquals(expectation, realResult);
    }

    @Test
    void compareDifferentFoodWhichIsNotSame() {
        boolean expectation = false;
        assertFoodIsInOrderList(new Order("해산물파스타-1"), new Order("제로콜라-2"), expectation);
    }

    @Test
    void createTwoOrdersWhichEachOfTotalPriceIsFiftyThousandAndThirtyThousand() {
        int expectedPrice1 = 50_000;
        assertTotalPrice(new Order("크리스마스파스타-2"), expectedPrice1);
        int expectedPrice2 = 30_000;
        assertTotalPrice(new Order("초코케이크-2"), expectedPrice2);
    }

    private void assertTotalPrice(Order order, int expectedPrice) {
        int realPrice = order.calculate();
        assertEquals(expectedPrice, realPrice);
    }
}
