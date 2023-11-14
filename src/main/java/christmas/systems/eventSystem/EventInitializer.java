package christmas.systems.eventSystem;

import christmas.event.evnets.gift.AmountToGiftEvent;
import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekDiscountEvent;
import java.util.ArrayList;
import java.util.List;

public class EventInitializer {
    private final List<LinearIncreaseDiscountEvent> linearIncreaseDiscountEvents = new ArrayList<>();
    private final List<AmountToGiftEvent> amountToGiftEvents = new ArrayList<>();
    private final List<SpecialDayDiscountEvent> specialDayDiscountEvents = new ArrayList<>();
    private final List<WeekDiscountEvent> weekDiscountEvents = new ArrayList<>();


    public void increaseEverydayDiscountEventsAdd(LinearIncreaseDiscountEvent linearIncreaseDiscountEvent) {
        linearIncreaseDiscountEvents.add(linearIncreaseDiscountEvent);
    }


    public void amountToGiftEventsAdd(AmountToGiftEvent amountToGiftEvent) {
        amountToGiftEvents.add(amountToGiftEvent);
    }

    public void specialDiscountEventAdd(SpecialDayDiscountEvent specialDayDiscountEvent) {
        specialDayDiscountEvents.add(specialDayDiscountEvent);
    }


    public void weekDiscountEventAdd(WeekDiscountEvent weekDiscountEvent) {
        weekDiscountEvents.add(weekDiscountEvent);
    }

    public List<LinearIncreaseDiscountEvent> getIncreaseEverydayDiscountEvents() {
        return linearIncreaseDiscountEvents;
    }

    public List<AmountToGiftEvent> getAmountToGiftEvents() {
        return amountToGiftEvents;
    }

    public List<SpecialDayDiscountEvent> getSpecialDiscountEvents() {
        return specialDayDiscountEvents;
    }

    public List<WeekDiscountEvent> getWeekDiscountEvents() {
        return weekDiscountEvents;
    }
}
