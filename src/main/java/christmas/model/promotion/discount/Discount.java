package christmas.model.promotion.discount;

import christmas.model.order.Order;
import christmas.model.promotion.PromotionManager;

public interface Discount {
    double apply(Order order, double originalAmount, PromotionManager manager);
}
