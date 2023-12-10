package christmas.model.promotion;

import christmas.model.menu.Menu;
import christmas.model.order.Order;
import christmas.model.promotion.discount.DiscountProcessor;
import christmas.model.promotion.freebie.FreebiePolicy;
import christmas.model.promotion.freebie.FreebieProcessor;
import christmas.model.reciept.BenefitResult;
import christmas.model.reciept.FreebieResult;
import christmas.model.reciept.PromotionResult;
import java.util.EnumMap;
import java.util.List;

public class PromotionManager {
    private EnumMap<Promotion, Double> benefitRecords;
    private EnumMap<Menu, Integer> freebies;
    private final DiscountProcessor discountProcessor;
    private final FreebieProcessor freebieProcessor;

    public PromotionManager(List<FreebiePolicy> freebiePolicies) {
        this.benefitRecords = new EnumMap<>(Promotion.class);
        this.freebies = new EnumMap<>(Menu.class);
        this.discountProcessor = new DiscountProcessor();
        this.freebieProcessor = new FreebieProcessor(freebiePolicies);
    }

    public void addFreebie(Menu freebie, int count) {
        this.freebies.merge(freebie, count, Integer::sum);
    }

    public void addBenefitRecord(Promotion type, double amount) {
        benefitRecords.merge(type, amount, Double::sum);
    }

    public PromotionResult getResult(Order order) {
        discountProcessor.process(order, this);
        freebieProcessor.process(order, this);

        return PromotionResult.from(BenefitResult.from(benefitRecords), FreebieResult.from(freebies));
    }
}
