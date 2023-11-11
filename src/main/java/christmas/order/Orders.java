package christmas.order;

import christmas.enums.menu.MenuItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Orders {
    private final List<Order> orderList;

    public Orders(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Integer calculateTotalPrice() {
        return orderList.stream().mapToInt(Order::calculatePrice).sum();
    }

    public Integer findEventMenuCount(MenuItem[] menuItems) {
        return orderList.stream().mapToInt(menu -> menu.findEventMenuCount(menuItems)).sum();
    }

    public List<Order> getOrderList() {
        return Collections.unmodifiableList(orderList);
    }
}
