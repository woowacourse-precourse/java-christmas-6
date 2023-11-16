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

class ChristmasDiscountTest {

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 25);

    GlobalEvent christmasDiscount = ChristmasDiscount.INSTANCE;

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertThat(christmasDiscount.getEventName()).isEqualTo("크리스마스 디데이 할인");
    }

    @ParameterizedTest
    @CsvSource({
            "2023-12-01, 1000",
            "2023-12-03, 1200",
            "2023-12-25, 3400",
            "2023-12-26, 0"
    })
    @DisplayName("크리스마스 이벤트 할인 적용 테스트")
    void applyDiscount(LocalDate date, double excepted) {
        Price dumpPrice = Price.of(142_000.0); // 만원 이상 이면 얼마든 상관 없음, 만원 이하면 할인 적용 x
        Price actual = (Price) christmasDiscount.applyEvent(new DecemberVisitDate(date), dumpPrice);
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
    @DisplayName("날짜에 대한 이벤트 가능 판단 테스트, 2023-12-1 부터 2023-12-31까지 가능")
    void isPossibleEvnet_날짜(LocalDate date, Double price, boolean expected) {
        assertThat(christmasDiscount.isPossibleEvent(new DecemberVisitDate(date), Price.of(price))).isEqualTo(expected);
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
        assertThat(christmasDiscount.isPossibleEvent(new DecemberVisitDate(date), Price.of(price))).isEqualTo(expected);
    }
}