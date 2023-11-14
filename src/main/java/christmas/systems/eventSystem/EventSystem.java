package christmas.systems.eventSystem;

import christmas.event.EventBenefit;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.event.evnets.gift.AmountToGiftEvent;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
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

        List<LinearIncreaseDiscountEvent> linearIncreaseDiscountEvents = eventInitializer.getIncreaseEverydayDiscountEvents();
        for (LinearIncreaseDiscountEvent linearIncreaseDiscountEvent : linearIncreaseDiscountEvents) {
            OneEventResult execute = linearIncreaseDiscountEvent.execute(reservationDate);
            oneEventResults.add(execute);
        }
        List<SpecialDayDiscountEvent> specialDayDiscountEvents = eventInitializer.getSpecialDiscountEvents();
        for (SpecialDayDiscountEvent specialDayDiscountEvent : specialDayDiscountEvents) {
            OneEventResult execute = specialDayDiscountEvent.execute(reservationDate);
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

        //TODO : 추가구현필요
        Gift gift = Gift.NO_GIFT();
        if (!gifts.isEmpty()) {
            gift = gifts.get(0);
        }

        return new EventBenefit(oneEventResults, gift);
    }
}
