package christmas.promotion.domain.event.discount;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.DecemberVisitDate;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SpecialDiscountTest {

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);

    GlobalEvent specialDiscount = SpecialDiscount.INSTANCE;

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertThat(specialDiscount.getEventName()).isEqualTo("특별 할인");
    }

    @ParameterizedTest
    @CsvSource({
            "2023-11-12, 0", // 11월 (x) 일요일
            "2023-12-01, 0", // 금요일 x
            "2023-12-03, 1000",
            "2023-12-24, 1000",
            "2023-12-25, 1000",
            "2024-12-25, 0" // 2024년 (x)
    })
    @DisplayName("특별 할인 적용 테스트, 2023-12-1 ~ 2023-12-31 & 일요일, 12월 25일만 적용")
    void applyDiscount(LocalDate date, double excepted) {
        Price dumpPrice = Price.of(142_000.0); // 만원 이상 이면 얼마든 상관 없음, 만원 이하면 할인 적용 x
        Price actual = (Price) specialDiscount.applyEvent(new DecemberVisitDate(date), dumpPrice);
        assertThat(actual.price()).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-11-30, 10000.0, false",
            "2024-12-10, 10000.0, false",
            "2023-12-10, 10000.0, true",
            "2023-12-25, 10000.0, true",
            "2023-12-26, 10000.0, false",
    })
    @DisplayName("날짜에 대한 이벤트 가능 판단 테스트,2023-12-1 ~ 2023-12-31 & 일요일, 12월 25일만 적용")
    void isPossibleEvnet_날짜(LocalDate date, Double price, boolean expected) {
        assertThat(specialDiscount.isPossibleEvent(new DecemberVisitDate(date), Price.of(price))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-12-25, -5000.0, false",
            "2023-12-25, 0.0, false",
            "2023-12-25, 10000.0, true",
            "2023-12-25, 300000.0, true",
    })
    @DisplayName("금액에 대한 이벤트 가능 판단 테스트, 최소 만원 이상 이어야 이벤트 가능")
    void isPossibleEvnet_금액(LocalDate date, Double price, boolean expected) {
        assertThat(specialDiscount.isPossibleEvent(new DecemberVisitDate(date), Price.of(price))).isEqualTo(expected);
    }
}