package christmas.model.promotion.freebie;

import christmas.model.order.Order;
import christmas.model.promotion.PromotionManager;
import java.util.List;

public class FreebieProcessor {
    private final List<FreebiePolicy> freebiePolicies;

    public FreebieProcessor(List<FreebiePolicy> freebiePolicies) {
        this.freebiePolicies = freebiePolicies;
    }

    public void process(Order order, PromotionManager manager) {
        for (FreebiePolicy policy : freebiePolicies) {
            policy.apply(order, manager);
        }
    }
}
