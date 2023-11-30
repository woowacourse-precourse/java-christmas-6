package christmas;

import christmas.domain.ReservationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationDateTest {
    @DisplayName("예약 날짜가 12월(1~31일)에 해당하지 않으면 예외발생")
    @Test
    void createReservationDateWhichIsNotBetweenOneAndThirtyOne() {
        assertThatThrownBy(() -> new ReservationDate(0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new ReservationDate(32))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void countdownBeforeChristmas() {
        int expectedDDayFromDecemberFirst = 24;
        assertDDay(new ReservationDate(1), expectedDDayFromDecemberFirst);
        int expectedDDayFromChristmas = 0;
        assertDDay(new ReservationDate(25), expectedDDayFromChristmas);
    }

    private void assertDDay(ReservationDate reservationDate, int dDay) {
        assertEquals(reservationDate.christmasCountdown(), dDay);
    }

    @Test
    void returnTrueWhenReserveOnWeekDay() {
        boolean expectation = true;
        assertIsWeekDay(new ReservationDate(3), expectation);
    }

    @Test
    void returnFalseWhenReserveOnWeekend() {
        boolean expectation = false;
        assertIsWeekDay(new ReservationDate(1), expectation);
    }

    private void assertIsWeekDay(ReservationDate reservationDate, boolean expectation) {
        boolean real = reservationDate.isWeekDay();
        assertEquals(real, expectation);
    }
}
