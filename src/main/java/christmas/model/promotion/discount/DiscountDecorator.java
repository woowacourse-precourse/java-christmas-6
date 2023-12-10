package christmas.model.promotion.discount;


import christmas.model.order.Order;
import christmas.model.promotion.PromotionManager;

public abstract class DiscountDecorator implements Discount {
    protected Discount decoratedDiscount;

    public DiscountDecorator(Discount decoratedDiscount) {
        this.decoratedDiscount = decoratedDiscount;
    }

    @Override
    public double apply(Order order, double originalAmount, PromotionManager manager) {
        return decoratedDiscount.apply(order, originalAmount, manager);
    }
}
