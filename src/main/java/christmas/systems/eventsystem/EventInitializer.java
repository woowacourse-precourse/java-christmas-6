package christmas.systems.eventsystem;

import christmas.event.evnets.gift.AmountToGiftEvent;
import christmas.event.evnets.increasediscount.LinerIncreaseDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekDiscountEvent;
import java.util.ArrayList;
import java.util.List;

public class EventInitializer {
    private final List<LinerIncreaseDiscountEvent> linerIncreaseDiscountEvents = new ArrayList<>();
    private final List<AmountToGiftEvent> amountToGiftEvents = new ArrayList<>();
    private final List<SpecialDayDiscountEvent> specialDayDiscountEvents = new ArrayList<>();
    private final List<WeekDiscountEvent> weekDiscountEvents = new ArrayList<>();


    public void increaseEverydayDiscountEventsAdd(LinerIncreaseDiscountEvent linerIncreaseDiscountEvent) {
        linerIncreaseDiscountEvents.add(linerIncreaseDiscountEvent);
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

    public List<LinerIncreaseDiscountEvent> getIncreaseEverydayDiscountEvents() {
        return linerIncreaseDiscountEvents;
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
