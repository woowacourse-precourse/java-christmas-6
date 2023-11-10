package christmas.order;

import christmas.event.EventBenefit;
import christmas.event.WooWaEventHandler;
import java.time.LocalDate;

public class OrderSystem {
    private final Orders orders;
    private final WooWaEventHandler wooWaEventHandler;
    private Integer amountOfBenefit;

    public OrderSystem(Orders orders, WooWaEventHandler wooWaEventHandler) {
        this.orders = orders;
        this.wooWaEventHandler = wooWaEventHandler;
    }

    public void calculateBenefitSituation(LocalDate reservationDate){
        EventBenefit eventBenefit = wooWaEventHandler.activateEvent(reservationDate, orders);

    }
}
