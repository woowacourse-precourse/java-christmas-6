package christmas.event;

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
        MainMenu[] weekdayDiscountMenus = MainMenu.values();
        DessertMenu[] weekendDiscountMenus = DessertMenu.values();

        EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
        EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);

        this.increaseEverydayDiscountEvent = new ChristmasDDayDiscount(typicalPeriod, 1000, 100);
        this.amountToGiftEvent = new AmountToAGiftEvent(monthPeriod, 120_000, CHAMPAGNE);
        this.specialDiscountEvent = new SpecialDayDiscountEvent(monthPeriod, 1000);
        this.weekdayDiscountEvent = new WeekdayDiscount(monthPeriod, weekdayDiscountMenus, 2023);
        this.weekendDiscountEvent = new WeekendDiscount(monthPeriod, weekendDiscountMenus, 2023);
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
        if(menuItem==null){
            return 0;
        }
        return menuItem.getPrice();
    }
}
