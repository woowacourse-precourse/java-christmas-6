package christmas;

import christmas.enums.MainMenu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OneOrderMenuTest {
    @DisplayName("티본 스테이크 Enum 과 주문한 수량을 반환한다.")
    @Test
    void basicTest() {
        //given
        OneOrderMenu oneOrderMenu = new OneOrderMenu(MainMenu.T_BONE_STEAK, 2);

        //when
        String menuName = oneOrderMenu.getMenuItem().getName();
        Integer orderQuantity = oneOrderMenu.getOrderQuantity();

        //then
        Assertions.assertThat(menuName).isEqualTo("티본스테이크");
        Assertions.assertThat(orderQuantity).isEqualTo(2);

    }
}