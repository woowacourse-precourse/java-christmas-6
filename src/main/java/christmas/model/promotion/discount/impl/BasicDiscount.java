package christmas.model.promotion.discount.impl;

import christmas.model.order.Order;
import christmas.model.promotion.PromotionManager;
import christmas.model.promotion.discount.Discount;

public class BasicDiscount implements Discount {
    @Override
    public double apply(Order order, double originalAmount, PromotionManager manager) {
        return order.getTotalAmount();
    }
}
