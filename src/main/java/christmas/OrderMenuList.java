package christmas;

import java.util.List;

public class OrderMenuList {
    private final List<OrderMenu> orderMenuList;

    public OrderMenuList(List<OrderMenu> orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    public Integer calculateTotalPrice(){
        return orderMenuList.stream().mapToInt(OrderMenu::calculatePrice).sum();
    }


}
