package christmas.order;


import static christmas.enums.events.decemberevent.GiftEvents.GIFT_EVENT;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKDAY_DISCOUNT;
import static christmas.enums.events.decemberevent.WeekDiscountEvents.WEEKEND_DISCOUNT;

import christmas.enums.menu.DessertMenu;
import christmas.systems.eventSystem.EventInitializer;
import christmas.systems.eventSystem.EventSystem;
import christmas.systems.ordersystem.OrderSystem;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReceiptTest {
    private final static Order oneIceCream = new Order(DessertMenu.ICE_CREAM, 1);
    private final static Orders ordersOneIceReam = new Orders(Set.of(oneIceCream));
    private final static LocalDate reservationDate = LocalDate.of(2023, Month.DECEMBER, 3);

    public EventSystem eventSystem() {
        EventInitializer eventInitializer = new EventInitializer();
        eventInitializer.increaseEverydayDiscountEventsAdd(CHRISTMAS_D_DAY_DISCOUNT.getInstance());
        eventInitializer.specialDiscountEventAdd(SPECIAL_DISCOUNT.getInstance());
        eventInitializer.amountToGiftEventsAdd(GIFT_EVENT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKDAY_DISCOUNT.getInstance());
        eventInitializer.weekDiscountEventAdd(WEEKEND_DISCOUNT.getInstance());

        return new EventSystem(eventInitializer);
    }

    @DisplayName("총주문금액 10000원 이하 시 이벤트 무효")
    @Test
    void name() {
        //given
        EventSystem eventSystem = eventSystem();
        OrderSystem orderSystem = new OrderSystem(eventSystem);

        //when
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, ordersOneIceReam);
        Boolean eligible = receipt.isEligible();

        //then
        Assertions.assertThat(eligible).isFalse();

    }
}