package christmas.systems.event;

import static christmas.enums.benefit.DiscountBenefit.MINIMUM_REQUIRE_AMOUNT;

import christmas.event.EventBenefit;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.List;

public class EventSystem {
    private final EventInitializer eventInitializer;

    public EventSystem(EventInitializer eventInitializer) {
        this.eventInitializer = eventInitializer;
    }


    public EventBenefit activateEvent(LocalDate reservationDate, Orders orders) {
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();

        if (!orders.isEligibleForDiscount(MINIMUM_REQUIRE_AMOUNT.getAmount())) {
            return EventBenefit.NO_EVENT_BENEFIT();
        }

        OneEventResult christmasDDayOneEventResult = eventInitializer.christmasDDayDiscount(reservationDate);
        OneEventResult specialDiscountOneEventResult = eventInitializer.specialDayDiscountEvent(reservationDate);
        OneEventResult weekdayEventResult = eventInitializer.weekdayDiscount(reservationDate, orders);
        OneEventResult weekendEventResult = eventInitializer.weekendDiscount(reservationDate, orders);
        Gift gift = eventInitializer.amountToAGiftEvent(reservationDate, totalPriceBeforeDiscount);

        List<OneEventResult> oneEventResults = List.of(christmasDDayOneEventResult, specialDiscountOneEventResult,
                weekdayEventResult, weekendEventResult);

        return new EventBenefit(oneEventResults, gift);
    }
}
