package christmas.views;

import static christmas.enums.menu.MainMenu.SEAFOOD_PASTA;
import static christmas.enums.menu.MainMenu.T_BONE_STEAK;

import christmas.order.Order;
import christmas.order.Orders;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MessagesTest {

    private final static Order twoTBoneSteak = new Order(T_BONE_STEAK, 2);
    private final static Order twoSeafoodPasta = new Order(SEAFOOD_PASTA, 2);
    private final static Orders orders = new Orders(Set.of(twoTBoneSteak, twoSeafoodPasta));

    @Test
    void repeatAllOrders() {
        String returnMessages = Messages.repeatAllOrders(orders);
        Assertions.assertThat(returnMessages).contains("티본스테이크 2개", "해산물파스타 2개");
    }
}