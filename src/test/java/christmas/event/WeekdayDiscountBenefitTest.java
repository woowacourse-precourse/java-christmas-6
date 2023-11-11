package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.benefit.DiscountBenefit;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.event.weekdiscount.WeekdayDiscount;
import christmas.order.OrderMenu;
import christmas.order.Orders;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

class WeekdayDiscountBenefitTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate weekday = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static LocalDate weekend = LocalDate.of(2023, Month.DECEMBER, 2);
    private final static WeekdayDiscount weekDayDiscount = new WeekdayDiscount(eventPeriod,MainMenu.values(),2023);
    private final static OrderMenu orderMenuWithDessert = new OrderMenu(DessertMenu.CHOCOLATE_CAKE,2);
    private final static OrderMenu orderMenuWithMain = new OrderMenu(MainMenu.T_BONE_STEAK,2);
    private final static Orders ordersWithMain = new Orders(List.of(orderMenuWithMain));
    private final static Orders ordersWithDessert = new Orders(List.of(orderMenuWithDessert));

    @DisplayName("평일이면 메인 수당 각각 2023원씩 할인한다.")
    @Test
    void weekDaySituation() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekday,ordersWithMain);

        //then
        assertThat(discountAmount).isEqualTo(WEEK_BENEFIT.getAmount()* orderMenuWithDessert.getOrderQuantity());
    }

    @DisplayName("주말이면 할인받지 아니한다.")
    @Test
    void weekendSituation() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekend,ordersWithMain);

        //then
        assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());
    }

    @DisplayName("평일이어도 다른 메뉴들은 해당되지 아니한다.")
    @Test
    void weekDaySituationWithNoDessert() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekday,ordersWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());
    }

    @DisplayName("주말이어도 다른 메뉴들은 해당되지 아니한다.")
    @Test
    void weekendSituationWithNoDessert() {
        //when
        Integer discountAmount = weekDayDiscount.execute(weekend,ordersWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());
    }
}