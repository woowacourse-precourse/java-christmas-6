package christmas.order;

import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.menu.MenuItem;
import christmas.event.EventBenefit;
import christmas.manangers.BadgeManager;
import christmas.manangers.WooWaEventManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class OrderSystem {
    private final WooWaEventManager wooWaEventManager;
    private final BadgeManager badgeManager;

    public OrderSystem(WooWaEventManager wooWaEventManager, BadgeManager badgeManager) {
        this.wooWaEventManager = wooWaEventManager;
        this.badgeManager = badgeManager;
    }

    public Receipt orderProcess(LocalDate reservationDate, Set<Order> unprocessedOrders){
        Orders orders = new Orders(unprocessedOrders);
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();

        EventBenefit eventBenefit = wooWaEventManager.activateEvent(reservationDate, orders);
        Integer discountBenefit = eventBenefit.discountBenefit();
        MenuItem gift = eventBenefit.gift();

        BenefitBadge badge = badgeManager.isBadgeConditionSatisfied(discountBenefit);

        return new Receipt(orders,totalPriceBeforeDiscount,discountBenefit,gift,badge);
    }

}
