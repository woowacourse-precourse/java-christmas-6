package christmas.domain.generators;

import christmas.domain.entity.Order;
import christmas.domain.entity.menu.Menu;
import christmas.domain.entity.menu.MenuBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderGeneratorsTest {

    @DisplayName("메뉴와 개수를 정확히 입력할 경우 order 객체가 반환된다.")
    @Test
    void orderGenerationTest(){
        //Arrange
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "시저샐러드-1,티본스테이크-2,해산물파스타-1,레드와인-1";
        Menu caesarSalad = MenuBoard.getMenus().get("시저샐러드");
        Menu tBoneSteak = MenuBoard.getMenus().get("티본스테이크");

        //Act
        Order order = orderGenerator.generateOrder(readLine, date);

        //Assert
        assertThat(order.getVisitDate()).isEqualTo(date);
        assertThat(order.getOrderMap().get(caesarSalad)).isEqualTo(1);
        assertThat(order.getOrderMap().get(tBoneSteak)).isEqualTo(2);
    }

    @DisplayName("주문 형식이 잘못된 경우 예외가 발생한다.")
    @Test
    void orderFormatTest(){
        //Arrange
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "티본스테이크2,샴페인1";

        //Assert
        assertThatThrownBy(() ->
                orderGenerator.generateOrder(readLine, date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 개수가 숫자가 아닐 경우 예외가 발생한다.")
    @Test
    void menuCountIsNotNumber(){
        //Arrange
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "티본스테이크-스테이크,샴페인-1";

        //Assert
        assertThatThrownBy(() ->
                orderGenerator.generateOrder(readLine, date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 개수가 20개를 넘을 경우 예외가 발생한다.")
    @Test
    void menuCountOver(){
        //Arrange
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "티본스테이크-200,샴페인-6";

        //Assert
        assertThatThrownBy(() ->
                orderGenerator.generateOrder(readLine, date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문할 경우 예외가 발생한다.")
    @Test
    void orderOnlyBeverage(){
        //Arrange
        Order.emptyMap();
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "제로콜라-10";

        //Assert
        assertThatThrownBy(() ->
                orderGenerator.generateOrder(readLine, date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("존재하지 않는 메뉴를 주문할 경우 예외가 발생한다.")
    @Test
    void orderMenuNotExist(){
        //Arrange
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "엉터리생고기-5";

        //Assert
        assertThatThrownBy(() ->
                orderGenerator.generateOrder(readLine, date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("중복된 메뉴를 주문할 경우 예외가 발생한다.")
    @Test
    void orderDuplicatedMenu(){
        //Arrange
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "타파스-3,샴페인-1,타파스-1";

        //Assert
        assertThatThrownBy(() ->
                orderGenerator.generateOrder(readLine, date)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 개수가 유효하지 않은 경우 예외가 발생한다.")
    @Test
    void menuCountValidate(){
        //Arrange
        OrderGenerator orderGenerator = new OrderGenerator();
        int date = 5;
        String readLine = "타파스-3,샴페인-0";

        //Assert
        assertThatThrownBy(() ->
                orderGenerator.generateOrder(readLine, date)).isInstanceOf(IllegalArgumentException.class);
    }
}
