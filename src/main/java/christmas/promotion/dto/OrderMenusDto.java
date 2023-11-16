package christmas.promotion.dto;

import christmas.promotion.domain.order.OrderMenu;

import java.util.List;

public record OrderMenusDto(List<OrderMenu> orderMenus) {
    public OrderMenusDto(final List<OrderMenu> orderMenus) {
        this.orderMenus = List.copyOf(orderMenus);
    }
}
