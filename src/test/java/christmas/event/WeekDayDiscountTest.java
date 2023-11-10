package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import christmas.enums.DessertMenu;
import christmas.order.OrderMenu;
import java.time.LocalDate;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekDayDiscountTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static LocalDate weekDay = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate weekend = LocalDate.of(2023, Month.DECEMBER, 2);
    private final static WeekDayDiscount weekDayDiscount = new WeekDayDiscount(startDate,endDate);
    private final static OrderMenu orderMenu = new OrderMenu(DessertMenu.CHOCOLATE_CAKE,2);
    private final static Integer DISCOUNT_AMOUNT = 2023;

    @DisplayName("평일이면 디저트 수당 각각 2023원씩 할인한다.")
    @Test
    void weekDaySituation() {
        //when
        Integer discountAmount = weekDayDiscount.executeEvent(weekDay, orderMenu);

        //then
        assertThat(discountAmount).isEqualTo(DISCOUNT_AMOUNT*orderMenu.getOrderQuantity());
    }

    @DisplayName("주말이면 할인받지 아니한다.")
    @Test
    void weekendSituation() {
        //when
        Integer discountAmount = weekDayDiscount.executeEvent(weekend, orderMenu);

        //then
        assertThat(discountAmount).isEqualTo(0);
    }
}