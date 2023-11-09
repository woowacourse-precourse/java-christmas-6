package christmas;

import static christmas.enums.MainMenu.T_BONE_STEAK;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {
    private final static String T_BONE_STEAK_NAME = "티본스테이크";
    private final static Integer TWO_ORDER = 2;
    private final static OrderMenu orderMenu = new OrderMenu(T_BONE_STEAK, TWO_ORDER);

    @DisplayName("티본 스테이크 Enum 과 주문한 수량을 반환한다.")
    @Test
    void nameAndQuantityReturn() {
        //when
        String menuName = orderMenu.getMenuItem().getName();
        Integer orderQuantity = orderMenu.getOrderQuantity();

        //then
        Assertions.assertThat(menuName).isEqualTo(T_BONE_STEAK_NAME);
        Assertions.assertThat(orderQuantity).isEqualTo(TWO_ORDER);

    }

    @DisplayName("자신의 값을 스스로 계산한다.")
    @Test
    void calculatePrice() {
        //when
        Integer price = orderMenu.calculatePrice();

        //then
        Assertions.assertThat(price).isEqualTo(T_BONE_STEAK.getPrice() * TWO_ORDER);
    }
}