package study;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

public class DateTest {

    @Test
    void 데이트_사용() {
        // 날짜 생성
        LocalDate date = LocalDate.of(2023, 12, 3);
        // 요일 가져오기
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        // 결과 출력
        System.out.println("12월 3일의 요일: " + dayOfWeek);
        System.out.println(dayOfWeek.getValue());

        // 날짜, 요일 가져오기
        date = LocalDate.of(2023, 12, 4);
        dayOfWeek = date.getDayOfWeek();
        // 결과 출력
        System.out.println("12월 4일의 요일: " + dayOfWeek);
        System.out.println(dayOfWeek.getValue());


    }

    @Test
    void 기간_출력() {
        // 시작 날짜와 종료 날짜 설정
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 6);

        // 날짜 간의 차이 계산
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // 결과 출력
        System.out.println("12월 1일부터 12월 6일까지 며칠이 지났는지: " + daysBetween + "일");
    }

    @Test
    void between() {
        LocalDate currentDate = LocalDate.of(2023, 12, 7);
        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 25);
        System.out.println(currentDate.isBefore(startDate) + " " + currentDate.isAfter(endDate));

    }

    @Test
    void between_Exception() {
        assertThatThrownBy(() -> LocalDate.of(2023, 12, 0))
                .isInstanceOf(DateTimeException.class);

        assertThatThrownBy(() -> LocalDate.of(2023, 12, 32))
                .isInstanceOf(DateTimeException.class);
    }
}
