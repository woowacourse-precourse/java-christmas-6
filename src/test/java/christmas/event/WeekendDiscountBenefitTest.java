package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.WEEK_BENEFIT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.event.weekdiscount.WeekendDiscount;
import christmas.order.Order;
import christmas.order.Orders;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekendDiscountBenefitTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate weekDay = LocalDate.of(2023, Month.DECEMBER, 3);
    private final static LocalDate weekend = LocalDate.of(2023, Month.DECEMBER, 2);
    private final static WeekendDiscount weekendDiscount = new WeekendDiscount(eventPeriod, DessertMenu.values(), 2023);
    private final static Order ORDER_WITH_DESSERT = new Order(DessertMenu.CHOCOLATE_CAKE, 2);
    private final static Order ORDER_WITH_MAIN = new Order(MainMenu.T_BONE_STEAK, 2);
    private final static Orders ordersWithDessert = new Orders(Set.of(ORDER_WITH_DESSERT));
    private final static Orders ordersWithMain = new Orders(Set.of(ORDER_WITH_MAIN));

    @DisplayName("주말이면 메인메뉴 수당 각각 2023원씩 할인한다.")
    @Test
    void weekDaySituationWithMain() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekend, ordersWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(WEEK_BENEFIT.getAmount() * ORDER_WITH_MAIN.getOrderQuantity());
    }

    @DisplayName("평일이면 할인하지 아니한다.")
    @Test
    void weekendSituationWithMain() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekDay, ordersWithDessert);

        //then
        assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());
    }

    @DisplayName("주말이어도 디저트가 아니라면 할인하지 아니한다.")
    @Test
    void weekendSituationWithDessert() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekend, ordersWithMain);

        //then
        assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());

    }

    @DisplayName("평일이어도 메인메뉴가 아니라면 할인하지 아니한다.")
    @Test
    void weekDaySituationWithDessert() {
        //when
        Integer discountAmount = weekendDiscount.execute(weekDay, ordersWithMain);

        //then
        assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());

    }
}