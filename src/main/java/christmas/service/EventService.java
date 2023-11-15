package christmas.service;

import christmas.domain.benefit.Benefit;
import christmas.domain.date.Date;
import christmas.domain.order.Order;
import christmas.domain.category.Category;
import christmas.dto.BenefitDto;
import christmas.dto.OrderDto;
import java.util.List;
import java.util.Map;

public class EventService {

    private Date date;
    private Order order;
    private Benefit benefit;

    public void initDate(final int date) {
        this.date = new Date(date);
    }

    public void initOrder(final Map<String, Integer> menus) {
        this.order = new Order(menus);
    }

    public void initBenefit() {
        this.benefit = new Benefit(date, order);
    }



    public OrderDto getOrderDto() {
        return order.toDto();
    }

    public BenefitDto getBenefitDto() {
        return benefit.toDto();
    }

    public int getEventDate() {
        return date.getDate();
    }
}
