package christmas.order;

import christmas.enums.menu.MenuItem;
import java.util.List;

public class Orders {
    private final List<OrderMenu> orderMenuList;

    public Orders(List<OrderMenu> orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    public Integer calculateTotalPrice() {
        return orderMenuList.stream().mapToInt(OrderMenu::calculatePrice).sum();
    }

    public Integer findEventMenuCount(MenuItem menuItem) {
        return orderMenuList.stream().mapToInt(menu -> menu.findEventMenuCount(menuItem)).sum();

    }

}
