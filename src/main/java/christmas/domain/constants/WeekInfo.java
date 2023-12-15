package christmas.domain.constants;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public enum WeekInfo {
    WEEKDAY(List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY)),
    WEEKEND(List.of(FRIDAY, SATURDAY));

    private final List<DayOfWeek> days;

    WeekInfo(List<DayOfWeek> days) {
        this.days = days;
    }

    public static WeekInfo from(DayOfWeek dayOfWeek) {
        return Arrays.stream(WeekInfo.values())
                .filter(element -> element.days.contains(dayOfWeek))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException());
    }
}
