package christmas;

import static christmas.enums.BeverageMenu.RED_WINE;
import static christmas.enums.MainMenu.T_BONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.order.OrderList;
import christmas.order.OrderMenu;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderListTest {

    private final static OrderMenu twoTBoneSteak = new OrderMenu(T_BONE_STEAK, 2);
    private final static OrderMenu twoRedWine = new OrderMenu(RED_WINE, 2);
    private final static List<OrderMenu> totalMenu = List.of(twoTBoneSteak, twoRedWine);
    private final static OrderList orderList = new OrderList(totalMenu);
    private final static Integer TOTAL_PRICE = 230000;

    @DisplayName("주문한 메뉴들의 총 가격을 구한다.")
    @Test
    void calculateTotalPrice() {
        //when
        Integer totalPrice = orderList.calculateTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(TOTAL_PRICE);

    }
}