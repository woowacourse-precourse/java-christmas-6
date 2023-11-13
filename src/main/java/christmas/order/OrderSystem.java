package christmas.order;

import christmas.enums.badge.benefit.BenefitBadge;
import christmas.enums.menu.MenuItem;
import christmas.event.EventBenefit;
import christmas.event.OneEventResult;
import christmas.manangers.WooWaEventManager;
import java.time.LocalDate;
import java.util.List;

public class OrderSystem {
    private final WooWaEventManager wooWaEventManager;

    public OrderSystem(WooWaEventManager wooWaEventManager) {
        this.wooWaEventManager = wooWaEventManager;
    }

    //TODO: 문제점
    public Receipt calculateOrderResult(LocalDate reservationDate, Orders orders){
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();

        EventBenefit eventBenefit = wooWaEventManager.activateEvent(reservationDate, orders);

        List<OneEventResult> oneEventResults = eventBenefit.oneEventResults();
        Integer totalDiscountBenefit = eventBenefit.showTotalDiscount();
        MenuItem gift = eventBenefit.gift();

        BenefitBadge badge = BenefitBadge.determineBadge(totalDiscountBenefit);

        return new Receipt(oneEventResults,totalPriceBeforeDiscount,totalDiscountBenefit,gift,badge);
    }

}
