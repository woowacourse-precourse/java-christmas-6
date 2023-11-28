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
        ReservationDate reservationDate1 = new ReservationDate(1);
        int resultDDay1= reservationDate1.christmasCountdown();
        int expectedDDay1 = 24;

        assertEquals(expectedDDay1, resultDDay1);

        ReservationDate reservationDate2 = new ReservationDate(25);
        int resultDDay2 = reservationDate2.christmasCountdown();
        int expectedDDay2 = 0;

        assertEquals(expectedDDay2, resultDDay2);
    }
}
