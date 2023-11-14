package christmas.event;

import static christmas.enums.benefit.DiscountBenefit.BASIC_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.INCREASE_BENEFIT;
import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;
import static christmas.enums.events.decemberevent.LinearDiscountEvents.CHRISTMAS_D_DAY_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.event.evnets.linearincreasediscount.LinearIncreaseDiscount;
import christmas.utils.EventPeriod;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LinearIncreaseDiscountEventBenefitTest {

    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 25);
    private final static EventPeriod eventPeriod = new EventPeriod(startDate, endDate);
    private final static LocalDate overDate = LocalDate.of(2023, Month.DECEMBER, 26);
    private final static LinearIncreaseDiscount INCREASE_DISCOUNT_UNTIL_TYPICAL_DAY = new LinearIncreaseDiscount(
            CHRISTMAS_D_DAY_DISCOUNT.getName(), eventPeriod,
            BASIC_BENEFIT.getAmount(), INCREASE_BENEFIT.getAmount());

    @DisplayName("날짜를 입력했을 때, 이벤트 날짜에 해당되지 않으면 0을 반환한다.")
    @Test
    void whenEventActivate() {

        Integer discountAmount = INCREASE_DISCOUNT_UNTIL_TYPICAL_DAY.execute(overDate).discountBenefit();

        assertThat(discountAmount).isEqualTo(NO_BENEFIT.getAmount());
    }

    @ParameterizedTest
    @MethodSource("provideInputForDiscountAmountTesting")
    @DisplayName("1일부터 25일까지 할인가 검증")
    public void dummy(LocalDate reservationDate, Integer expectedDiscountAmount) {
        //when
        Integer discountAmount = INCREASE_DISCOUNT_UNTIL_TYPICAL_DAY.execute(reservationDate).discountBenefit();

        //then
        assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

    static Stream<Arguments> provideInputForDiscountAmountTesting() {
        Stream.Builder<Arguments> streamBuilder = Stream.builder();
        for (int i = 1; i <= endDate.getDayOfMonth(); i++) {
            streamBuilder.add(Arguments.of(LocalDate.of(2023, Month.DECEMBER, i),
                    BASIC_BENEFIT.getAmount() + (i - 1) * INCREASE_BENEFIT.getAmount()));
        }
        return streamBuilder.build();
    }


}