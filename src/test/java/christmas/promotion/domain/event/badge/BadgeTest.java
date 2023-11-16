package christmas.promotion.domain.event.badge;

import christmas.promotion.domain.visitdate.DecemberVisitDate;
import christmas.promotion.vo.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class BadgeTest {

    private static final LocalDate EVENT_PERIOD_START = LocalDate.of(2023, 12, 1);
    private static final LocalDate EVENT_PERIOD_END = LocalDate.of(2023, 12, 31);


    @ParameterizedTest
    @CsvSource({
            "2023-11-12, 0, 없음", // 11월 (x)
            "2023-12-01, 5000, 별",
            "2023-12-03, 8000, 별",
            "2023-12-24, 10000, 트리",
            "2023-12-25, 20000, 산타",
            "2023-12-25, 300000, 산타",
            "2024-12-25, 300000, 없음, " // 2024년 (x)
    })
    @DisplayName("배지 적용 테스트, 2023-12-1 ~ 2023-12-31 & 금액에 따른 배지 수여")
    void applyDiscount(LocalDate date, double orderPrice, String badgeName) {
        /**
         * 5천 원 이상: 별
         * 1만 원 이상: 트리
         * 2만 원 이상: 산타
         */

        Badge actualBadge = Badge.NONE;
        for (Badge badge : Badge.values()) {
            if (badge.isPossibleEvent(new DecemberVisitDate(date), Price.of(orderPrice))) {
                actualBadge = badge.applyEvent(new DecemberVisitDate(date), Price.of(orderPrice));
                break;
            }
        }
        assertThat(actualBadge.getName()).isEqualTo(badgeName);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-11-30, 0, false",
            "2024-12-10, 0, false",
            "2023-12-10, 5000, true",
            "2023-12-25, 5000, true",
            "2023-12-26, 5000, true",
    })
    @DisplayName("날짜에 대한 이벤트 가능 판단 테스트,2023-12-1 ~ 2023-12-31")
    void isPossibleEvnet_날짜(LocalDate date, double price, boolean expected) {
        assertThat(Badge.NONE.isPossibleEvent(new DecemberVisitDate(date), Price.of(price))).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "2023-12-25, -5000.0, 없음",
            "2023-12-25, 0.0, 없음",
            "2023-12-25, 5000, 별",
            "2023-12-25, 12000.0, 트리",
            "2023-12-25, 300000.0, 산타",
    })
    @DisplayName("금액에 대한 이벤트 가능 판단 테스트, 최소 5000원 이상 이어야 이벤트 가능")
    void isPossibleEvnet_금액(LocalDate date, double price, String expectedBadgeName) {
        Badge actualBadge = Badge.NONE;
        for (Badge badge : Badge.values()) {
            if (badge.isPossibleEvent(new DecemberVisitDate(date), Price.of(price))) {
                actualBadge = badge;
                break;
            }
        }
        assertThat(actualBadge.getName()).isEqualTo(expectedBadgeName);
    }
}