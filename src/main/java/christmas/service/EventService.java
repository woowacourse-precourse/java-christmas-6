package christmas.service;

import christmas.domain.date.Date;
import christmas.domain.order.Order;
import java.util.Map;

public class EventService {

    private Date date;
    private Order menus;

    public void initDate(final int date) {
        this.date = new Date(date);
    }

    public void initMenu(final Map<String, Integer> menus) {
        this.menus = new Order(menus);
        System.out.println(this.menus.getMenus());
    }

}
