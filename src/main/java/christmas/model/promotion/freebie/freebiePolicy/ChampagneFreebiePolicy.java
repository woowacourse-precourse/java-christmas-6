package christmas.model.promotion.freebie.freebiePolicy;

import static christmas.common.Constant.CHAMPAGNE_FREEBIE_THRESHOLD;

import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.promotion.Promotion;
import christmas.model.promotion.PromotionManager;
import christmas.model.promotion.freebie.FreebiePolicy;

public class ChampagneFreebiePolicy implements FreebiePolicy {
    private final int CHAMPAGNE_FREEBIE_COUNT = 1;

    @Override
    public void apply(Order order, PromotionManager manager) {
        if (isApplicable(order)) {
            manager.addFreebie(Menu.CHAMPAGNE, CHAMPAGNE_FREEBIE_COUNT);
            manager.addBenefitRecord(Promotion.FREEBIE_PROMOTION, calculateBenefitAmount());
        }
    }

    @Override
    public boolean isApplicable(Order order) {
        return order.isTotalAmountMoreThan(CHAMPAGNE_FREEBIE_THRESHOLD);
    }

    private double calculateBenefitAmount() {
        return Menu.CHAMPAGNE.getPrice() * CHAMPAGNE_FREEBIE_COUNT;
    }
}
