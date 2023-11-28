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
        Order orderInList1 = new Order("해산물파스타-1");
        Order order1 = new Order("해산물파스타-2");
        boolean result = orderInList1.isOrderSameAsOrderInList(order1);
        boolean expected = true;

        assertEquals(expected, result);
    }

    @Test
    void compareDifferentFoodWhichIsNotSame() {
        Order orderInList1 = new Order("해산물파스타-1");
        Order order1 = new Order("제로콜라-2");
        boolean result = orderInList1.isOrderSameAsOrderInList(order1);
        boolean expected = false;

        assertEquals(expected, result);
    }

    @Test
    void createTwoOrdersWhichEachOfTotalPriceIsFiftyThousandAndThirtyThousand() {
        Order order1 = new Order("크리스마스파스타-2");
        int resultPrice1 = order1.calculate();

        int expectedPrice1 = 50_000;

        assertEquals(expectedPrice1, resultPrice1);

        Order order2 = new Order("초코케이크-2");
        int resultPrice2 = order2.calculate();

        int expectedPrice2 = 30_000;

        assertEquals(expectedPrice2, resultPrice2);
    }
}
