package christmas.model.promotion.discount.impl;

import christmas.model.order.Order;
import christmas.model.order.VisitDate;
import christmas.model.promotion.Promotion;
import christmas.model.promotion.PromotionManager;
import christmas.model.promotion.discount.Discount;
import christmas.model.promotion.discount.DiscountDecorator;
import java.time.LocalDate;

public class ChristmasDdayDiscount extends DiscountDecorator {
    private static final LocalDate CHRISTMAS_DDAY_DISCOUNT_FIRST_DAY = LocalDate.of(2023, 12, 1);
    private static final LocalDate CHRISTMAS_DDAY_DISCOUNT_LAST_DAY = LocalDate.of(2023, 12, 25);

    public ChristmasDdayDiscount(Discount discount) {
        super(discount);
    }

    @Override
    public double apply(Order order, double originalPrice, PromotionManager manager) {
        double discountedPrice = decoratedDiscount.apply(order, originalPrice, manager);
        VisitDate visitDate = order.getVisitDate();

        if (isApplicable(visitDate)) {
            double discountAmount = calculateDiscountAmount(visitDate);
            double resultPrice = discountedPrice - discountAmount;

            manager.addBenefitRecord(Promotion.CHRISTMAS_DDAY_DISCOUNT, discountAmount);

            return resultPrice;
        }
        return discountedPrice;
    }

    private boolean isApplicable(VisitDate visitDate) {
        return visitDate.isDateInPeriod(CHRISTMAS_DDAY_DISCOUNT_FIRST_DAY, CHRISTMAS_DDAY_DISCOUNT_LAST_DAY);
    }

    private double calculateDiscountAmount(VisitDate visitDate) {
        return 1000 + 100 * (visitDate.getDay() - 1);
    }
}
