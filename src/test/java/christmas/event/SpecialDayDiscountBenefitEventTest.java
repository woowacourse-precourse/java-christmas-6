package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.*;

import christmas.event.specialdiscount.SpecialDayDiscountEvent;
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
    private final static SpecialDayDiscountEvent SPECIAL_DAY_DISCOUNT_EVENT = new SpecialDayDiscountEvent(eventPeriod,1000);

    @DisplayName("특별 할인이 적용되는 날은 1000원을 추가할인한다.")
    @Test
    void reservationSpecialDate() {
        Integer discountAmount = SPECIAL_DAY_DISCOUNT_EVENT.execute(specialDate);
        Assertions.assertThat(discountAmount).isEqualTo(BASIC_BENEFIT.getAmount());
    }

    @DisplayName("특별 할인이 적용되지 않는 날은 1000원을 추가할인하지 않는다.")
    @Test
    void reservationNoNSpecialDate() {
        Integer discountAmount = SPECIAL_DAY_DISCOUNT_EVENT.execute(nonSpecialDate);
        Assertions.assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());
    }
}