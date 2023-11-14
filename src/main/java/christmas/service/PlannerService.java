package christmas.service;

import christmas.domain.Customer;
import christmas.domain.Order;
import christmas.dto.DiscountInfoDto;
import christmas.type.MenuType;
import christmas.type.WeekDateType;

import java.util.HashMap;
import java.util.Map;

public class PlannerService {
    public static final int CHRISTMAS_DAY = 25;
    public static final int BASE_MONEY = 1000;
    public static final int INCREASEMENT_MONEY = 100;
    public static final int EVENT_PRICE = 120000;
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

    public int getTotalPrice() {
        return order.getTotalPrice();
    }

    public Map<String, Integer> calculateDiscount(int date) {
        setChristmasDayDiscount(date);
        setSpecialDayDiscount(date);
        setNormalDayDiscount(date);
        setGivingEventDiscount(order.getTotalPrice());
        return DiscountInfoDto.of(customer.getDiscount());
    }

    public int getTotalDiscount() {
        return customer.getTotalDiscountPrice();
    }

    private void setChristmasDayDiscount(int date) {
        if (date <= CHRISTMAS_DAY) {
            int money = BASE_MONEY + date * INCREASEMENT_MONEY;
            customer.setChristmasDiscountPrice(money);
        }
    }

    private void setGivingEventDiscount(int totalPrice) {
        if (totalPrice >= EVENT_PRICE) {
            customer.setGivingEventDiscount(MenuType.CHAMPAGNE.getPrice());
            customer.isGiven
        }
    }

    private void setNormalDayDiscount(int date) {
        if (!WeekDateType.isDateIncluded(date)) {
            customer.setSpecialDiscountPrice(
                    order.getDessertFoods().stream()
                            .mapToInt(f -> f.getCount())
                            .sum()
            );
        }
    }

    private void setSpecialDayDiscount(int date) {
        if (WeekDateType.isDateIncluded(date)) {
            customer.setSpecialDiscountPrice(
                    order.getMainFoods().stream()
                            .mapToInt(f -> f.getCount())
                            .sum()
            );
        }
    }

    public boolean isBiggerThanPricePresent() {
        return customer.isGiven();
    }
}
