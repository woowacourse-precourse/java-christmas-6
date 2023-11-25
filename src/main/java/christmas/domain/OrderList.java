package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class OrderList {
    private final List<Order> orders;

    public OrderList(String[] inputOrders){
        List<Order> orders = new ArrayList<>();
        for(String inputOrder : inputOrders){
            Order order = new Order(inputOrder);
            findDuplicatedOrderInOrderList(order, orders);
            orders.add(order);
        }
        this.orders = orders;
    }

    private void findDuplicatedOrderInOrderList(Order order, List<Order> orders) throws IllegalArgumentException {
        for(Order orderInList : orders){
            if(orderInList.isOrderSameAsOrderInList(order)){
                throw new IllegalArgumentException();
            }
        }
    }
}
