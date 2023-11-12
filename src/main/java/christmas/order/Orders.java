package christmas.order;

import christmas.enums.menu.MenuItem;
import java.util.Collections;
import java.util.Set;

public class Orders {
    private final Set<Order> orderSet;

    public Orders(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public Integer calculateTotalPrice() {
        return orderSet.stream().mapToInt(Order::calculatePrice).sum();
    }

    public Integer findEventMenuCount(MenuItem[] menuItems) {
        return orderSet.stream().mapToInt(menu -> menu.findEventMenuCount(menuItems)).sum();
    }

    public Set<Order> getOrderSet() {
        return Collections.unmodifiableSet (orderSet);
    }
}
