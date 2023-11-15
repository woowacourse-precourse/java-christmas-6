package christmas.dto;

import java.util.Map;

public class OrderDto {

    private final Map<String, Integer> order;
    private final int totalPrice;

    public OrderDto(final Map<String, Integer> order, final int totalPrice) {
        this.order = order;
        this.totalPrice = totalPrice;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
