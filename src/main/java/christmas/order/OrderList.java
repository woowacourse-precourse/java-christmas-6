package christmas.order;

import java.util.List;

public class OrderList {
    private final List<OrderMenu> orderMenuList;

    public OrderList(List<OrderMenu> orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    public Integer calculateTotalPrice() {
        return orderMenuList.stream().mapToInt(OrderMenu::calculatePrice).sum();
    }


}
