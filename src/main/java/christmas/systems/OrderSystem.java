package christmas.systems;

import christmas.enums.badge.benefit.BenefitBadge;
import christmas.event.EventBenefit;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.systems.event.EventSystem;
import java.time.LocalDate;
import java.util.List;

public class OrderSystem {
    private final EventSystem eventSystem;

    public OrderSystem(EventSystem eventSystem) {
        this.eventSystem = eventSystem;
    }

    //TODO: 문제점
    public Receipt calculateOrderResult(LocalDate reservationDate, Orders orders) {
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();

        EventBenefit eventBenefit = eventSystem.activateEvent(reservationDate, orders);

        List<OneEventResult> oneEventResults = eventBenefit.oneEventResults();
        Integer totalDiscountBenefit = eventBenefit.showTotalDiscount();
        Gift gift = eventBenefit.gift();

        BenefitBadge badge = BenefitBadge.determineBadge(totalDiscountBenefit);

        return new Receipt(oneEventResults, totalPriceBeforeDiscount, totalDiscountBenefit, gift, badge);
    }

}
