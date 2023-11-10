package christmas.event;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChristmasDDayDiscountTest {

    private final static LocalDate startDate = LocalDate.of(2023, Month.DECEMBER, 1);
    private final static LocalDate endDate = LocalDate.of(2023, Month.DECEMBER, 25);
    private final static LocalDate overDate = LocalDate.of(2023, Month.DECEMBER, 26);
    private final static ChristmasDDayDiscount christmasDDayDiscount = new ChristmasDDayDiscount(startDate, endDate);
    private final static Integer DISCOUNT_START_AMOUNT = 1000;
    private final static Integer DISCOUNT_INCREMENT_PER_DAY = 100;

    @DisplayName("날짜를 입력했을 때, 이벤트 날짜에 해당되지 않으면 0을 반환한다.")
    @Test
    void whenEventActivate() {

        Integer discountAmount = christmasDDayDiscount.executeEvent(overDate);

        assertThat(discountAmount).isEqualTo(0);
    }

    @ParameterizedTest
    @MethodSource("provideInputForDiscountAmountTesting")
    @DisplayName("1일부터 25일까지 할인가 검증")
    public void dummy(LocalDate reservationDate, Integer expectedDiscountAmount) {
        //when
        Integer discountAmount = christmasDDayDiscount.executeEvent(reservationDate);

        //then
        assertThat(discountAmount).isEqualTo(expectedDiscountAmount);
    }

    static Stream<Arguments> provideInputForDiscountAmountTesting() {
        Stream.Builder<Arguments> streamBuilder = Stream.builder();
        for (int i = 1; i <= endDate.getDayOfMonth(); i++) {
            streamBuilder.add(Arguments.of(LocalDate.of(2023, Month.DECEMBER, i),
                    DISCOUNT_START_AMOUNT + (i - 1) * DISCOUNT_INCREMENT_PER_DAY));
        }
        return streamBuilder.build();
    }


}