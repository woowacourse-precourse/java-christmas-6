package christmas;

import christmas.domain.ReservationDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ReservationDateTest {
    @DisplayName("예약 날짜가 12월(1~31일)에 해당하지 않으면 예외발생")
    @Test
    void createReservationDateWhichIsNotBetweenOneAndThirtyOne() {
        assertThatThrownBy(() -> new ReservationDate(0))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new ReservationDate(32))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
