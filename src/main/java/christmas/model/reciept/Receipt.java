package christmas.model.reciept;

import christmas.model.menu.Menu;
import christmas.model.order.MenuCount;
import christmas.model.order.Order;
import christmas.model.order.VisitDate;
import christmas.model.promotion.DecemberEventBadge;
import christmas.model.promotion.Promotion;
import java.util.EnumMap;

public class Receipt {
    private final Order order;
    private final PromotionResult promotionResult;

    private Receipt(Order order, PromotionResult promotionResult) {
        this.order = order;
        this.promotionResult = promotionResult;
    }

    public static Receipt issue(Order order, PromotionResult promotionResult) {
        return new Receipt(order, promotionResult);
    }

    public double getTotalOrderAmount() {
        return order.getTotalAmount();
    }

    private double getTotalDiscountedAmount() {
        return promotionResult.getTotalDiscountedAmount();
    }

    public double getTotalBenefitAmount() {
        return promotionResult.getTotalBenefitAmount();
    }

    public double getEstimatedPaymentAmount() {
        return getTotalOrderAmount() - getTotalDiscountedAmount();
    }

    public VisitDate getVisitDate() {
        return order.getVisitDate();
    }

    public String getEventBadge() {
        return DecemberEventBadge.getBadgeForTotalBenefitAmount(getTotalBenefitAmount()).getKorean();
    }

    public EnumMap<Menu, MenuCount> getOrders() {
        return order.getOrders();
    }

    public EnumMap<Menu, Integer> getFreebies() {
        return promotionResult.getFreebies();
    }

    public EnumMap<Promotion, Double> getBenefitRecords() {
        return promotionResult.getBenefitRecords();
    }
}
