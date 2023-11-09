package christmas;

import static christmas.enums.MainMenu.T_BONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.MenuItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderMenuTest {
    private final static String T_BONE_STEAK_NAME = "티본스테이크";
    private final static Integer TWO_ORDER = 2;
    private final static OrderMenu orderMenu = new OrderMenu(T_BONE_STEAK, TWO_ORDER);
    private final static Integer TWO_T_BONE_STEAK_PRISE = T_BONE_STEAK.getPrice() * TWO_ORDER;

    @DisplayName("티본 스테이크 Enum 과 주문한 수량을 반환한다.")
    @Test
    void nameAndQuantityReturn() {
        //when
        String menuName = orderMenu.getMenuItem().getName();
        Integer orderQuantity = orderMenu.getOrderQuantity();

        //then
        assertThat(menuName).isEqualTo(T_BONE_STEAK_NAME);
        assertThat(orderQuantity).isEqualTo(TWO_ORDER);

    }

    @DisplayName("자신의 값을 스스로 계산한다.")
    @Test
    void calculatePrice() {
        //when
        Integer price = orderMenu.calculatePrice();

        //then
        assertThat(price).isEqualTo(TWO_T_BONE_STEAK_PRISE);
    }

    @DisplayName("메뉴 이름과 수량으로 OrderMenu 를 생성한다.")
    @Test
    void createOrderMenu() {
        //given
        OrderMenu findOrderMenu = OrderMenu.createOrderMenu("티본스테이크", 2);

        //when
        Integer price = findOrderMenu.calculatePrice();
        Integer orderQuantity = findOrderMenu.getOrderQuantity();
        MenuItem menuItem = findOrderMenu.getMenuItem();

        //then
        assertThat(price).isEqualTo(TWO_T_BONE_STEAK_PRISE);
        assertThat(orderQuantity).isEqualTo(TWO_ORDER);
        assertThat(menuItem).isEqualTo(T_BONE_STEAK);

    }
}