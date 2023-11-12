package christmas.manangers;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.GIFT_CONDITION_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.MINIMUM_REQUIRE_AMOUNT;
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
import christmas.event.EventBenefit;
import christmas.event.EventPeriod;
import christmas.event.gift.AmountToAGiftEvent;
import christmas.event.gift.AmountToGiftEvent;
import christmas.event.increasediscount.ChristmasDDayDiscount;
import christmas.event.increasediscount.IncreaseEverydayDiscountEvent;
import christmas.event.specialdiscount.SpecialDayDiscountEvent;
import christmas.event.specialdiscount.SpecialDiscountEvent;
import christmas.event.weekdiscount.WeekDiscountEvent;
import christmas.event.weekdiscount.WeekdayDiscount;
import christmas.event.weekdiscount.WeekendDiscount;
import christmas.utils.MenuList;
import christmas.order.Orders;
import java.time.LocalDate;

public class WooWaEventManager {
    private final AmountToGiftEvent amountToGiftEvent;
    private final IncreaseEverydayDiscountEvent increaseEverydayDiscountEvent;
    private final SpecialDiscountEvent specialDiscountEvent;
    private final WeekDiscountEvent weekdayDiscountEvent;
    private final WeekDiscountEvent weekendDiscountEvent;

    public WooWaEventManager() {
        final MenuItem[] weekdayMenus = MainMenu.values();
        final MenuItem[] weekendMenus = DessertMenu.values();

        EventPeriod monthPeriod = EventPeriod.createMonthPeriod(YEAR.getDate(), MONTH.getDate());
        EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(YEAR.getDate(), MONTH.getDate(),
                START_OF_THE_MONTH.getDate(), END_OF_THE_CHRISTMAS.getDate());

        this.increaseEverydayDiscountEvent = new ChristmasDDayDiscount(typicalPeriod, BASIC_BENEFIT.getAmount(),
                INCREASE_BENEFIT.getAmount());
        this.amountToGiftEvent = new AmountToAGiftEvent(monthPeriod, GIFT_CONDITION_BENEFIT.getAmount(), CHAMPAGNE);
        this.specialDiscountEvent = new SpecialDayDiscountEvent(monthPeriod, BASIC_BENEFIT.getAmount());
        this.weekdayDiscountEvent = new WeekdayDiscount(monthPeriod, weekdayMenus, WEEK_BENEFIT.getAmount());
        this.weekendDiscountEvent = new WeekendDiscount(monthPeriod, weekendMenus, WEEK_BENEFIT.getAmount());
    }

    private record activateWeekEvent(Integer weekdayDiscount, Integer weekendDiscount) {

    }
    private record activateDateEvent(Integer christmasDiscount, Integer specialDiscount) {

    }

    public EventBenefit activateEvent(LocalDate reservationDate, Orders orders) {
        Integer totalPriceBeforeDiscount = orders.calculateTotalPrice();
        if(totalPriceBeforeDiscount < MINIMUM_REQUIRE_AMOUNT.getAmount()){
            return new EventBenefit(null,NO_BENEFIT.getAmount());
        }
        activateDateEvent dateEvent = this.getActivateDateEvent(reservationDate);
        activateWeekEvent weekEvent = this.getActivateWeekEvent(reservationDate, orders);
        String giftName = this.giftEvent(reservationDate, totalPriceBeforeDiscount);

        MenuItem gift = MenuList.getMenuByName(giftName);
        int discountBenefit = this.calculateTotalBenefit(dateEvent, weekEvent, gift);
        return new EventBenefit(gift, discountBenefit);
    }

    private int calculateTotalBenefit(activateDateEvent dateEvent, activateWeekEvent weekEvent, MenuItem gift) {
        Integer giftPrice = addGiftPriceToBenefitAmount(gift);
        return dateEvent.christmasDiscount() + dateEvent.specialDiscount() + weekEvent.weekdayDiscount() + weekEvent.weekendDiscount()
                + giftPrice;
    }

    private String giftEvent(LocalDate reservationDate, Integer totalPriceBeforeDiscount) {
        return amountToGiftEvent.execute(reservationDate, totalPriceBeforeDiscount);
    }
    private activateDateEvent getActivateDateEvent(LocalDate reservationDate) {
        Integer christmasDiscount = increaseEverydayDiscountEvent.execute(reservationDate);
        Integer specialDiscount = specialDiscountEvent.execute(reservationDate);
        return new activateDateEvent(christmasDiscount, specialDiscount);
    }

    private activateWeekEvent getActivateWeekEvent(LocalDate reservationDate, Orders orders) {
        Integer weekdayDiscount = weekdayDiscountEvent.execute(reservationDate, orders);
        Integer weekendDiscount = weekendDiscountEvent.execute(reservationDate, orders);
        return new activateWeekEvent(weekdayDiscount, weekendDiscount);
    }

    private Integer addGiftPriceToBenefitAmount(MenuItem menuItem) {
        if (menuItem == null) {
            return NO_BENEFIT.getAmount();
        }
        return menuItem.getPrice();
    }
}
