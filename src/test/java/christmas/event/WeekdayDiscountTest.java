package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.DessertMenu;
import christmas.enums.MainMenu;
import christmas.event.weekdiscount.WeekdayDiscount;
import christmas.order.OrderMenu;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekdayDiscountTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate weekDay = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate weekend = LocalDate.of(2023, Month.DECEMBER, 2);
    private final static WeekdayDiscount weekDayDiscount = new WeekdayDiscount(eventPeriod,2023);
    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE,2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK,2);
    private final static Integer DISCOUNT_AMOUNT = 2023;
    private final static Integer NO_DISCOUNT_AMOUNT =0;

    @DisplayName("평일이면 디저트 수당 각각 2023원씩 할인한다.")
    @Test
    void weekDaySituation() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekDay, orderMenuWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(DISCOUNT_AMOUNT* orderMenuWithDessert.getOrderQuantity());
    }

    @DisplayName("주말이면 할인받지 아니한다.")
    @Test
    void weekendSituation() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekend, orderMenuWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(NO_DISCOUNT_AMOUNT);
    }

    @DisplayName("평일이어도 다른 메뉴들은 해당되지 아니한다.")
    @Test
    void weekDaySituationWithNoDessert() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekDay, orderMenuWithMain);

        //then
        assertThat(discountAmount).isEqualTo(NO_DISCOUNT_AMOUNT);
    }

    @DisplayName("주말이어도 다른 메뉴들은 해당되지 아니한다.")
    @Test
    void weekendSituationWithNoDessert() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekend, orderMenuWithMain);

        //then
        assertThat(discountAmount).isEqualTo(NO_DISCOUNT_AMOUNT);
    }
}