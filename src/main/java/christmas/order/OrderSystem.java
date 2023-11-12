package christmas.order;

import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.menu.MenuItem;
import christmas.event.EventBenefit;
import christmas.event.EventResult;
import christmas.manangers.BadgeManager;
import christmas.manangers.WooWaEventManager;
import java.time.LocalDate;
import java.util.List;

public class OrderSystem {
    private final WooWaEventManager wooWaEventManager;
    private final BadgeManager badgeManager;

    public OrderSystem(WooWaEventManager wooWaEventManager, BadgeManager badgeManager) {
        this.wooWaEventManager = wooWaEventManager;
        this.badgeManager = badgeManager;
    }

    public Receipt orderProcess(LocalDate reservationDate, Orders orders){
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();

        EventBenefit eventBenefit = wooWaEventManager.activateEvent(reservationDate, orders);
        List<EventResult> eventResults = eventBenefit.eventResults();
        Integer discountBenefit = eventBenefit.showDiscountBenefits();
        MenuItem gift = eventBenefit.gift();

        BenefitBadge badge = badgeManager.isBadgeConditionSatisfied(discountBenefit);

        return new Receipt(eventResults,totalPriceBeforeDiscount,discountBenefit,gift,badge);
    }

}
