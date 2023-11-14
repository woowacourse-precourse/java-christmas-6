package christmas.systems.eventSystem;

import christmas.event.EventBenefit;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.event.evnets.gift.AmountToGiftEvent;
import christmas.event.evnets.increasediscount.IncreaseEverydayDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekDiscountEvent;
import christmas.order.Orders;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventSystem {
    private final EventInitializer eventInitializer;

    public EventSystem(EventInitializer eventInitializer) {
        this.eventInitializer = eventInitializer;
    }


    public EventBenefit activateEvent(LocalDate reservationDate, Orders orders) {
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();
        List<OneEventResult> oneEventResults = new ArrayList<>();
        List<Gift> gifts = new ArrayList<>();

        List<IncreaseEverydayDiscountEvent> increaseEverydayDiscountEvents = eventInitializer.getIncreaseEverydayDiscountEvents();
        for (IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent : increaseEverydayDiscountEvents) {
            OneEventResult execute = increaseEverydayDiscountEvent.execute(reservationDate);
            oneEventResults.add(execute);
        }
        List<SpecialDiscountEvent> specialDiscountEvents = eventInitializer.getSpecialDiscountEvents();
        for (SpecialDiscountEvent specialDiscountEvent : specialDiscountEvents) {
            OneEventResult execute = specialDiscountEvent.execute(reservationDate);
            oneEventResults.add(execute);
        }
        List<WeekDiscountEvent> weekDiscountEvents = eventInitializer.getWeekDiscountEvents();
        for (WeekDiscountEvent weekDiscountEvent : weekDiscountEvents) {
            OneEventResult execute = weekDiscountEvent.execute(reservationDate, orders);
            oneEventResults.add(execute);
        }
        List<AmountToGiftEvent> amountToGiftEvents = eventInitializer.getAmountToGiftEvents();
        for (AmountToGiftEvent amountToGiftEvent : amountToGiftEvents) {
            Gift execute = amountToGiftEvent.execute(reservationDate, totalPriceBeforeDiscount);
            gifts.add(execute);
        }
        Gift gift = Gift.NO_GIFT();
        if (!gifts.isEmpty()) {
            gift = gifts.get(0);
        }

        return new EventBenefit(oneEventResults, gift);
    }
}
