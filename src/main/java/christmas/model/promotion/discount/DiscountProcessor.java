package christmas.model.promotion.discount;

import static christmas.common.Constant.DISCOUNT_THRESHOLD;

import christmas.model.order.Order;
import christmas.model.promotion.PromotionManager;
import christmas.model.promotion.discount.impl.BasicDiscount;
import christmas.model.promotion.discount.impl.ChristmasDdayDiscount;
import christmas.model.promotion.discount.impl.SpecialDiscount;
import christmas.model.promotion.discount.impl.WeekDayDiscount;
import christmas.model.promotion.discount.impl.WeekendDiscount;

public class DiscountProcessor {
    public void process(Order order, PromotionManager manager) {
        Discount discount = new BasicDiscount();

        if (order.isTotalAmountMoreThan(DISCOUNT_THRESHOLD)) {
            discount = new ChristmasDdayDiscount(discount);
            discount = new WeekDayDiscount(discount);
            discount = new WeekendDiscount(discount);
            discount = new SpecialDiscount(discount);
        }

        discount.apply(order, order.getTotalAmount(), manager);
    }
}
