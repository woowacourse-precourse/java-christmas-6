package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.DessertMenu;
import christmas.enums.MainMenu;
import christmas.event.weekdiscount.WeekendDiscount;
import christmas.order.OrderMenu;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate weekDay = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate weekend = LocalDate.of(2023, Month.DECEMBER, 2);
    private final static WeekendDiscount weekendDiscount = new WeekendDiscount(eventPeriod,2023);
    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK, 2);
    private final static Integer DISCOUNT_AMOUNT = 2023;
    private final static Integer NO_DISCOUNT_AMOUNT = 0;

    @DisplayName("주말이면 메인메뉴 수당 각각 2023원씩 할인한다.")
    @Test
    void weekDaySituationWithMain() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekend, orderMenuWithMain);

        //then
        assertThat(discountAmount).isEqualTo(DISCOUNT_AMOUNT * orderMenuWithMain.getOrderQuantity());
    }

    @DisplayName("평일이면 할인하지 아니한다.")
    @Test
    void weekendSituationWithMain() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekDay, orderMenuWithMain);

        //then
        assertThat(discountAmount).isEqualTo(NO_DISCOUNT_AMOUNT);
    }

    @DisplayName("주말이어도 메인메뉴가 아니라면 할인하지 아니한다.")
    @Test
    void weekendSituationWithDessert() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekend, orderMenuWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(NO_DISCOUNT_AMOUNT);

    }

    @DisplayName("평일이어도 메인메뉴가 아니라면 할인하지 아니한다.")
    @Test
    void weekDaySituationWithDessert() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekDay, orderMenuWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(NO_DISCOUNT_AMOUNT);

    }
}