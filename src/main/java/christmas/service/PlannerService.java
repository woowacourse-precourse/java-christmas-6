package christmas.service;

import christmas.domain.Customer;
import christmas.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class PlannerService {
    private Customer customer;
    private Order order = new Order();
    public Customer setVisitedDate(int date) {
        customer = new Customer(date);
        return customer;
    }

    public void setOrder(Map<String, Integer> menuMap) {
        for (String foodName : menuMap.keySet()) {
            order.addOrder(foodName, menuMap.get(foodName));
        }
    }

    public HashMap<String, Integer> getOrder() {
        HashMap<String, Integer> orderMap = order.getTotalOrder();
        return orderMap;
    }
}
