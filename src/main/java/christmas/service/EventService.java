package christmas.service;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import christmas.domain.category.Category;
import christmas.dto.OrderDto;
import java.util.List;
import java.util.Map;

public class EventService {

    private Date date;
    private Order order;

    public void initDate(final int date) {
        this.date = new Date(date);
    }

    public void initOrder(final Map<String, Integer> menus) {
        this.order = new Order(menus);
    }

    public OrderDto getOrderDto() {
        return order.toDto();
    }

    public int getEventDate() {
        return date.getDate();
    }
}
