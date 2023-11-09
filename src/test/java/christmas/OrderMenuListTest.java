package christmas;

import static christmas.enums.BeverageMenu.*;
import static christmas.enums.MainMenu.*;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuListTest {

    private final static OrderMenu twoTBoneSteak = new OrderMenu(T_BONE_STEAK,2);
    private final static OrderMenu twoRedWine = new OrderMenu(RED_WINE,2);
    private final static List<OrderMenu> totalMenu = List.of(twoTBoneSteak, twoRedWine);
    private final static OrderMenuList orderMenuList = new OrderMenuList(totalMenu);
    private final static Integer TOTAL_PRICE = 230000;
    @DisplayName("주문한 메뉴들의 총 가격을 구한다.")
    @Test
    void calculateTotalPrice() {
        //when
        Integer totalPrice = orderMenuList.calculateTotalPrice();

        //then
        assertThat(totalPrice).isEqualTo(TOTAL_PRICE);

    }
}