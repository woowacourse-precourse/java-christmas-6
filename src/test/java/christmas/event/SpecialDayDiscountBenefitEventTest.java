package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;
import static christmas.enums.events.decemberevent.SpecialDiscount.SPECIAL_DISCOUNT;

import christmas.event.evnets.specialdiscount.SpecialDayDiscount;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SpecialDayDiscountBenefitEventTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate specialDate = LocalDate.of(2023, Month.DECEMBER, 25);
    private final static LocalDate nonSpecialDate = LocalDate.of(2023, Month.DECEMBER, 26);
    private final static SpecialDayDiscount SPECIAL_DAY_DISCOUNT_EVENT = new SpecialDayDiscount(
            SPECIAL_DISCOUNT.getName(), eventPeriod, 1000);

    @DisplayName("특별 할인이 적용되는 날은 1000원을 추가할인한다.")
    @Test
    void reservationSpecialDate() {
        OneEventResult oneEventResult = SPECIAL_DAY_DISCOUNT_EVENT.execute(specialDate);
        Assertions.assertThat(oneEventResult.discountBenefit()).isEqualTo(BASIC_BENEFIT.getAmount());
    }

    @DisplayName("특별 할인이 적용되지 않는 날은 1000원을 추가할인하지 않는다.")
    @Test
    void reservationNoNSpecialDate() {
        OneEventResult oneEventResult = SPECIAL_DAY_DISCOUNT_EVENT.execute(nonSpecialDate);
        Assertions.assertThat(oneEventResult.discountBenefit()).isEqualTo(NO_BENEFIT.getAmount());
    }
}