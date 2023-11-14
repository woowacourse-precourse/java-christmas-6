package christmas.service;

import christmas.domain.Customer;
import christmas.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class PlannerService {
    public static final int CHRISTMAS_DAY = 25;
    public static final int BASE_MONEY = 1000;
    public static final int INCREASEMENT_MONEY = 100;
    private Customer customer;
    private Order order = new Order();
    public Customer setVisitedDate(int date) {
        customer = new Customer(date);
        if (date <= CHRISTMAS_DAY) {
            int money = BASE_MONEY + date * INCREASEMENT_MONEY;
            customer.setChristmasDiscountPrice(money);
        }
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

    public int getTotalPrice() {
        return order.getTotalPrice();
    }

    public void getDiscountContent() {

    }
}
