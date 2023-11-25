package christmas;

import christmas.domain.OrderList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderListTest {
    @Test
    void addOrderWhichIsAlreadyInOrderList() {
        assertThatThrownBy(() -> new OrderList(new String[] {"해산물파스타-2", "해산물파스타-1"}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
