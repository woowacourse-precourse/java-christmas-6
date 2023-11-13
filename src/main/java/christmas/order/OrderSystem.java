package christmas.order;

import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.menu.MenuItem;
import christmas.event.EventBenefit;
import christmas.event.OneEventResult;
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

    //TODO: 문제점
    public Receipt calculateOrderResult(LocalDate reservationDate, Orders orders){
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();

        EventBenefit eventBenefit = wooWaEventManager.activateEvent(reservationDate, orders);

        List<OneEventResult> oneEventResults = eventBenefit.oneEventResults();
        Integer discountBenefit = eventBenefit.showTotalDiscount();
        MenuItem gift = eventBenefit.gift();

        BenefitBadge badge = badgeManager.isBadgeConditionSatisfied(discountBenefit);

        return new Receipt(oneEventResults,totalPriceBeforeDiscount,discountBenefit,gift,badge);
    }

}
