package christmas.promotion.domain.visitdate;

import org.junit.jupiter.api.Test;

import christmas.promotion.exception.VisitDayException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


class DecemberVisitDateTest {

    @Test
    void issBetweenDates() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate("15");
        LocalDate start = LocalDate.of(2023, 12, 1);
        LocalDate end = LocalDate.of(2023, 12, 31);
        assertThat(decemberVisitDate.isBetweenDates(start, end)).isTrue();
    }

    @Test
    void isNotBetweenDates() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate("5");
        LocalDate start = LocalDate.of(2023, 12, 10);
        LocalDate end = LocalDate.of(2023, 12, 20);
        assertThat(decemberVisitDate.isBetweenDates(start, end)).isFalse();
    }

    @Test
    void isWeekday() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate("10");
        assertThat(decemberVisitDate.isWeekday()).isTrue();
    }

    @Test
    void isNotWeekday() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate("15");
        assertThat(decemberVisitDate.isWeekday()).isFalse();
    }

    @Test
    void isWeekend() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate("15");
        assertThat(decemberVisitDate.isWeekend()).isTrue();
    }

    @Test
    void isNotWeekend() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate("10");
        assertThat(decemberVisitDate.isWeekend()).isFalse();
    }

    @Test
    void isSunday() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate(LocalDate.of(2023, 12, 10));
        assertThat(decemberVisitDate.isSunday()).isTrue();
    }

    @Test
    void isNotSunday() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate(LocalDate.of(2023, 12, 12));
        assertThat(decemberVisitDate.isSunday()).isFalse();
    }

    @Test
    void isChristmas() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate(LocalDate.of(2023, 12, 25));
        assertThat(decemberVisitDate.isChristmas()).isTrue();
    }

    @Test
    void iNotChristmas() {
        DecemberVisitDate decemberVisitDate = new DecemberVisitDate(LocalDate.of(2023, 12, 10));
        assertThat(decemberVisitDate.isChristmas()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1", // 시작
            "12",
            "25",
            "31" // 끝
    })
    @DisplayName("유저 방문 날짜 입력 정상 케이스")
    void VisitDate_정상(String input) {
        DecemberVisitDate DecemberVisitDate = new DecemberVisitDate(input);
        assertThat(DecemberVisitDate.getVisitDay()).isEqualTo(Integer.parseInt(input));

        DecemberVisitDate newDecemberVisitDate = new DecemberVisitDate(input);

        assertThat(DecemberVisitDate.getVisitDay()).isEqualTo(newDecemberVisitDate.getVisitDay()); // 동등성 성립 O
        assertThat(DecemberVisitDate).hasSameHashCodeAs(newDecemberVisitDate); // 같은 값을 가지면, 해시 코드 같음
        assertThat(DecemberVisitDate).isNotSameAs(newDecemberVisitDate); // 동일성 성립 X
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "as",
            "",
            " ",
            "-1",
            "0",
            "32",
            "213"
    })
    @DisplayName("유저 방문 날짜 입력 예외 케이스")
    void VisitDate_예외(String input) {
        assertThatThrownBy(() -> new DecemberVisitDate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(VisitDayException.ErrorMessage.VISIT_DAY_ERROR.getMessage());
    }

    @Test
    @DisplayName("동등성 테스트")
    void equalsTest() {
        DecemberVisitDate decemberVisitDate1 = new DecemberVisitDate("1");
        DecemberVisitDate decemberVisitDate2 = new DecemberVisitDate("1");
        DecemberVisitDate decemberVisitDate3 = new DecemberVisitDate("2");

        assertAll(
                () -> assertThat(decemberVisitDate1).isEqualTo(decemberVisitDate1),
                () -> assertThat(decemberVisitDate1).isEqualTo(decemberVisitDate2).isEqualTo(decemberVisitDate1),
                () -> assertThat(decemberVisitDate2).isEqualTo(decemberVisitDate1),
                () -> assertThat(decemberVisitDate1).isNotEqualTo(decemberVisitDate3),
                () -> assertThat(decemberVisitDate1).isNotEqualTo(null)
        );
    }

    @Test
    @DisplayName("해시 코드, 동일성 테스트")
    void hashCodeTest() {
        DecemberVisitDate decemberVisitDate1 = new DecemberVisitDate("1");
        DecemberVisitDate decemberVisitDate2 = new DecemberVisitDate("1");

        assertThat(decemberVisitDate1).isEqualTo(decemberVisitDate2);
        assertThat(decemberVisitDate1.hashCode()).isEqualTo(decemberVisitDate2.hashCode());

        assertThat(decemberVisitDate1).isNotSameAs(decemberVisitDate2);
    }
}