package christmas;

import static christmas.enums.menu.MainMenu.SEAFOOD_PASTA;
import static christmas.enums.menu.MainMenu.T_BONE_STEAK;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    private final static String T_BONE_STEAK_NAME = "티본스테이크";
    private final static Integer TWO_ORDER = 2;
    private final static Order ORDER = new Order(T_BONE_STEAK, TWO_ORDER);
    private final static Order twoSeafoodPastaOrder = new Order(SEAFOOD_PASTA, TWO_ORDER);
    private final static Integer TWO_T_BONE_STEAK_PRISE = T_BONE_STEAK.getAmount() * TWO_ORDER;

    @DisplayName("티본 스테이크 Enum 과 주문한 수량을 반환한다.")
    @Test
    void nameAndQuantityReturn() {
        //when
        String menuName = ORDER.getMenuItem().getName();
        Integer orderQuantity = ORDER.getOrderQuantity();

        //then
        assertThat(menuName).isEqualTo(T_BONE_STEAK_NAME);
        assertThat(orderQuantity).isEqualTo(TWO_ORDER);

    }

    @DisplayName("자신의 값을 스스로 계산한다.")
    @Test
    void calculatePrice() {
        //when
        Integer price = ORDER.calculatePrice();

        //then
        assertThat(price).isEqualTo(TWO_T_BONE_STEAK_PRISE);
    }

    @DisplayName("메뉴 이름과 수량으로 OrderMenu 를 생성한다.")
    @Test
    void createOrderMenu() {
        //given
        Order findOrder = Order.createOrderMenu(T_BONE_STEAK_NAME, TWO_ORDER);

        //when
        Integer price = findOrder.calculatePrice();
        Integer orderQuantity = findOrder.getOrderQuantity();
        MenuItem menuItem = findOrder.getMenuItem();

        //then
        assertThat(price).isEqualTo(TWO_T_BONE_STEAK_PRISE);
        assertThat(orderQuantity).isEqualTo(TWO_ORDER);
        assertThat(menuItem).isEqualTo(T_BONE_STEAK);

    }

    @DisplayName("주문 수량을 반환한다.")
    @Test
    void findEventMenuCount() {
        //when
        Integer eventMenuCount = twoSeafoodPastaOrder.findEventMenuCount(MainMenu.values());

        //then
        assertThat(eventMenuCount).isEqualTo(TWO_ORDER);
    }
}