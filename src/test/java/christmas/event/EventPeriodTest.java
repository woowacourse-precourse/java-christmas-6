package christmas.event;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventPeriodTest {

    @DisplayName("년도와 달을 넣으면 한달 기간을 반환한다")
    @Test
    void createMonthPeriod() {
        //when
        EventPeriod monthPeriod = EventPeriod.createMonthPeriod(2023, 12);
        LocalDate startDate = monthPeriod.startDate();
        LocalDate endDate = monthPeriod.endDate();

        //then
        assertThat(startDate).isEqualTo(LocalDate.of(2023,Month.DECEMBER,1));
        assertThat(endDate).isEqualTo(LocalDate.of(2023,Month.DECEMBER,31));
    }

    @DisplayName("년도와 월 시작일 종료일을 넣으면 해당 기간을 반환한다.")
    @Test
    void createTypicalPeriod() {
        //when
        EventPeriod typicalPeriod = EventPeriod.createTypicalPeriod(2023, 12, 1, 25);

        //then
        assertThat(typicalPeriod.startDate()).isEqualTo(LocalDate.of(2023,Month.DECEMBER,1));
        assertThat(typicalPeriod.endDate()).isEqualTo(LocalDate.of(2023,Month.DECEMBER,25));

    }

    @DisplayName("시작일부터 N주 만큼의 이벤트 기간을 반환한다.")
    @Test
    void createTypicalWeekPeriod() {
        EventPeriod typicalWeekPeriod = EventPeriod.createTypicalWeekPeriod(2023, 12, 1, 1);
        assertThat(typicalWeekPeriod.endDate()).isEqualTo(LocalDate.of(2023,Month.DECEMBER,7));
    }
}