package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.GIFT_CONDITION_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static christmas.enums.date.decemberevent.DecemberEvent.END_OF_THE_CHRISTMAS;
import static christmas.enums.date.decemberevent.DecemberEvent.MONTH;
import static christmas.enums.date.decemberevent.DecemberEvent.START_OF_THE_MONTH;
import static christmas.enums.date.decemberevent.DecemberEvent.YEAR;
import static christmas.enums.menu.BeverageMenu.CHAMPAGNE;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.event.amounttogift.AmountToAGiftEvent;
import christmas.event.amounttogift.AmountToGiftEvent;
import christmas.event.increasediscount.ChristmasDDayDiscount;
import christmas.event.increasediscount.IncreaseEverydayDiscountEvent;
import christmas.event.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.specialdiscount.SpecialDiscountEvent;
import christmas.event.weekdiscount.WeekDiscountEvent;
import christmas.event.weekdiscount.WeekdayDiscount;
import christmas.event.weekdiscount.WeekendDiscount;
import christmas.order.MenuList;
import christmas.order.Orders;
import java.time.LocalDate;

public class WooWaEventHandler {
    private final AmountToGiftEvent amountToGiftEvent;
    private final IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent;
    private final SpecialDiscountEvent specialDiscountEvent;
    private final WeekDiscountEvent weekdayDiscountEvent;
    private final WeekDiscountEvent weekendDiscountEvent;

    public WooWaEventHandler() {
        final MainMenu[] weekdayDiscountMenus = MainMenu.values();
        final DessertMenu[] weekendDiscountMenus = DessertMenu.values();

        EventPeriod monthPeriod = EventPeriod.createMonthPeriod(YEAR.getDate(), MONTH.getDate());
        EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(YEAR.getDate(), MONTH.getDate(),
                START_OF_THE_MONTH.getDate(), END_OF_THE_CHRISTMAS.getDate());

        this.increaseEverydayDiscountEvent = new ChristmasDDayDiscount(typicalPeriod, BASIC_BENEFIT.getAmount(),
                INCREASE_BENEFIT.getAmount());
        this.amountToGiftEvent = new AmountToAGiftEvent(monthPeriod, GIFT_CONDITION_BENEFIT.getAmount(), CHAMPAGNE);
        this.specialDiscountEvent = new SpecialDayDiscountEvent(monthPeriod, BASIC_BENEFIT.getAmount());
        this.weekdayDiscountEvent = new WeekdayDiscount(monthPeriod, weekdayDiscountMenus, WEEK_BENEFIT.getAmount());
        this.weekendDiscountEvent = new WeekendDiscount(monthPeriod, weekendDiscountMenus, WEEK_BENEFIT.getAmount());
    }

    public EventBenefit activateEvent(LocalDate reservationDate, Orders orders) {
        Integer christmasDiscount = increaseEverydayDiscountEvent.execute(reservationDate);
        Integer specialDiscount = specialDiscountEvent.execute(reservationDate);
        Integer weekdayDiscount = weekdayDiscountEvent.execute(reservationDate, orders);
        Integer weekendDiscount = weekendDiscountEvent.execute(reservationDate, orders);
        String giftName = amountToGiftEvent.execute(reservationDate, orders);

        MenuItem gift = MenuList.getMenuByName(giftName);
        Integer giftPrice = addGiftPriceToBenefitAmount(gift);
        int discountBenefit = christmasDiscount + specialDiscount + weekdayDiscount + weekendDiscount + giftPrice;

        return new EventBenefit(gift, discountBenefit);
    }

    public Integer addGiftPriceToBenefitAmount(MenuItem menuItem) {
        if (menuItem == null) {
            return NO_BENEFIT.getAmount();
        }
        return menuItem.getPrice();
    }
}
