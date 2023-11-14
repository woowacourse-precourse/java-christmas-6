package christmas.systems.eventSystem;

import christmas.event.evnets.gift.AmountToGiftEvent;
import christmas.event.evnets.increasediscount.IncreaseEverydayDiscountEvent;
import christmas.event.evnets.specialdiscount.SpecialDiscountEvent;
import christmas.event.evnets.weekdiscount.WeekDiscountEvent;
import java.util.ArrayList;
import java.util.List;

public class EventInitializer {
    private final List<IncreaseEverydayDiscountEvent> increaseEverydayDiscountEvents =new ArrayList<>();
    private final List<AmountToGiftEvent> amountToGiftEvents =new ArrayList<>();
    private final List<SpecialDiscountEvent> specialDiscountEvents =new ArrayList<>();
    private final List<WeekDiscountEvent> weekDiscountEvents =new ArrayList<>();


    public void increaseEverydayDiscountEventsAdd(IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent){
        increaseEverydayDiscountEvents.add(increaseEverydayDiscountEvent);
    }

    private void increaseEverydayDiscountEventsRemove(IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent){
        increaseEverydayDiscountEvents.remove(increaseEverydayDiscountEvent);
    }

    public void amountToGiftEventsAdd(AmountToGiftEvent amountToGiftEvent){
        amountToGiftEvents.add(amountToGiftEvent);
    }

    private void amountToGiftEventsRemove(AmountToGiftEvent amountToGiftEvent){
        amountToGiftEvents.remove(amountToGiftEvent);
    }

    public void specialDiscountEventAdd(SpecialDiscountEvent specialDiscountEvent){
        specialDiscountEvents.add(specialDiscountEvent);
    }

    private void specialDiscountEventRemove(SpecialDiscountEvent specialDiscountEvent){
        specialDiscountEvents.remove(specialDiscountEvent);
    }

    public void weekDiscountEventAdd(WeekDiscountEvent weekDiscountEvent){
        weekDiscountEvents.add(weekDiscountEvent);
    }

    private void weekDiscountEventRemove(WeekDiscountEvent weekDiscountEvent){
        weekDiscountEvents.remove(weekDiscountEvent);
    }

    public List<IncreaseEverydayDiscountEvent> getIncreaseEverydayDiscountEvents() {
        return increaseEverydayDiscountEvents;
    }

    public List<AmountToGiftEvent> getAmountToGiftEvents() {
        return amountToGiftEvents;
    }

    public List<SpecialDiscountEvent> getSpecialDiscountEvents() {
        return specialDiscountEvents;
    }

    public List<WeekDiscountEvent> getWeekDiscountEvents() {
        return weekDiscountEvents;
    }
}
