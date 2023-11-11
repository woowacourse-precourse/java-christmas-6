package christmas.event;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventPeriodTest {
    private final static LocalDate startOfTheMonth = LocalDate.of(2023,Month.DECEMBER,1);
    private final static LocalDate endOfTheMonth = LocalDate.of(2023,Month.DECEMBER,31);
    private final static LocalDate endOfTheChristmas = LocalDate.of(2023,Month.DECEMBER,25);
    private final static LocalDate oneWeekAfterDay = LocalDate.of(2023,Month.DECEMBER,7);

    @DisplayName("년도와 달을 넣으면 한달 기간을 반환한다")
    @Test
    void createMonthPeriod() {
        //when
        EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
        LocalDate startDate = monthPeriod.startDate();
        LocalDate endDate = monthPeriod.endDate();

        //then
        assertThat(startDate).isEqualTo(startOfTheMonth);
        assertThat(endDate).isEqualTo(endOfTheMonth);
    }

    @DisplayName("년도와 월 시작일 종료일을 넣으면 해당 기간을 반환한다.")
    @Test
    void createTypicalPeriod() {
        //when
        EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);

        //then
        assertThat(typicalPeriod.startDate()).isEqualTo(startOfTheMonth);
        assertThat(typicalPeriod.endDate()).isEqualTo(endOfTheChristmas);

    }

    @DisplayName("시작일부터 N주 만큼의 이벤트 기간을 반환한다.")
    @Test
    void createTypicalWeekPeriod() {
        EventPeriod typicalWeekPeriod = EventPeriod.createTypicalWeekPeriod(2023, 12, 1, 1);
        assertThat(typicalWeekPeriod.endDate()).isEqualTo(oneWeekAfterDay);
    }
}