package christmas.event;

import java.time.LocalDate;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SpecialDiscountEventTest {
    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 31);
    private final static LocalDate specialDate = LocalDate.of(2023, Month.DECEMBER, 25);
    private final static LocalDate nonSpecialDate = LocalDate.of(2023, Month.DECEMBER, 26);
    private final static  SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent(startDate,endDate);

    @Test
    void reservationSpecialDate() {
        Integer discountAmount = specialDiscountEvent.executeDateDiscountEvent(specialDate);
        Assertions.assertThat(discountAmount).isEqualTo(1000);
    }

    @Test
    void reservationNoNSpecialDate() {
        Integer discountAmount = specialDiscountEvent.executeDateDiscountEvent(nonSpecialDate);
        Assertions.assertThat(discountAmount).isEqualTo(0);
    }
}