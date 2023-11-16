package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.GlobalEvent;
import christmas.promotion.vo.Price;
import christmas.promotion.domain.visitdate.DecemberVisitDate;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChampagneGiftTest {

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);

    GlobalEvent champagneGift = ChampagneGift.INSTANCE;

    @Test
    @DisplayName("이벤트 이름 확인")
    void eventName() {
        assertThat(champagneGift.getEventName()).isEqualTo("증정 이벤트");
    }

    @ParameterizedTest
    @CsvSource({
            "2023-11-12, 120000, 0",
            "2023-12-01, 80000, 0",
            "2023-12-03, 120000, 25000",
            "2023-12-24, 3000000, 25000",
            "2024-12-25, 3000000, 0"
    })
    @DisplayName("샴페인 할인 적용 테스트, 2023-12-1 ~ 2023-12-31 & 주문 가격이 12만원 이상인 경우 증정(25000원)")
    void applyDiscount(LocalDate date, double orderPrice, double excepted) {
        Price actual = (Price) champagneGift.applyEvent(new DecemberVisitDate(date), Price.of(orderPrice));

        assertThat(actual.price()).isEqualTo(excepted);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-11-30, 120000.0, false",
            "2024-12-10, 120000.0, false",
            "2023-12-10, 120000.0, true",
            "2023-12-25, 120000.0, true",
            "2023-12-26, 120000.0, true",
    })
    @DisplayName("날짜에 대한 이벤트 가능 판단 테스트,2023-12-1 ~ 2023-12-31 & 주문 금액이 12만원 이상이어야 함")
    void isPossibleEvnet_날짜(LocalDate date, Double orderPrice, boolean expected) {
        assertThat(champagneGift.isPossibleEvent(new DecemberVisitDate(date), Price.of(orderPrice))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-12-25, -5000.0, false",
            "2023-12-25, 0.0, false",
            "2023-12-25, 10_000.0, false",
            "2023-12-25, 120_000.0, true",
            "2023-12-25, 300_000.0, true",
    })
    @DisplayName("금액에 대한 이벤트 가능 판단 테스트, 최소 12 이상 이어야 이벤트 가능")
    void isPossibleEvnet_금액(LocalDate date, Double price, boolean expected) {
        assertThat(champagneGift.isPossibleEvent(new DecemberVisitDate(date), Price.of(price))).isEqualTo(expected);
    }
}