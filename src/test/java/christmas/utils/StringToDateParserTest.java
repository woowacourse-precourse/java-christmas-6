package christmas.utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import christmas.exceptions.IllegalDateFormatException;
import java.time.LocalDate;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringToDateParserTest {

    @DisplayName("문자열을 넣으면 LocalDate를 반환한다.")
    @Test
    void makeReservation() {
        String input ="1";
        LocalDate reservation = StringToDateParser.makeReservation(2023, 12, input);
        int year = reservation.getYear();
        Month month = reservation.getMonth();
        int date = reservation.getDayOfMonth();

        //then
        assertThat(year).isEqualTo(2023);
        assertThat(month).isEqualTo(Month.DECEMBER);
        assertThat(date).isEqualTo(1);
    }

    @DisplayName("문자열이 숫자가 아니면 예외가 발생한다")
    @Test
    public void whenNotInt() {
        assertThatThrownBy(()->StringToDateParser.makeReservation(2023,12,"day")).isInstanceOf(
                IllegalDateFormatException.class);
    }

    @DisplayName("입력값이 해당 월에 대한 범위를 벗어나면 예외가 발생한다.")
    @Test
    public void whenOverMonthDate() {
        assertThatThrownBy(()->StringToDateParser.makeReservation(2023,12,"99999999999999999999935")).isInstanceOf(
                IllegalDateFormatException.class);
    }
}