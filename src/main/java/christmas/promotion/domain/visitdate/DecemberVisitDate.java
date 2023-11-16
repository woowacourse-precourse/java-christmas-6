package christmas.promotion.domain.visitdate;

import christmas.promotion.exception.VisitDayException;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static christmas.promotion.domain.event.Event.EVENT_MONTH;
import static christmas.promotion.domain.event.Event.EVENT_YEAR;

public class DecemberVisitDate {
    private static final int DECEMBER_START_DAY = 1;
    private static final int DECEMBER_END_DAY = 31;
    private static final LocalDate CHRISTMAS = LocalDate.of(2023, 12, 25);

    //Integer 는 -128 ~ 127까지 캐싱 되므로, 1 ~ 31일은 캐싱 데이터를 안 만들었다.
    private final LocalDate visitDate;

    public DecemberVisitDate(final String value) {
        validate(value);
        int visitDay = Integer.parseInt(value);
        this.visitDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, visitDay);
    }

    public DecemberVisitDate(final LocalDate value) {
        this.visitDate = value;
    }

    private void validate(String visitDay) {
        validateInteger(visitDay);
        validateRangeVisitDay(visitDay);
    }

    private void validateInteger(final String visitDay) {
        try {
            Integer.parseInt(visitDay);
        } catch (IllegalArgumentException exception) {
            throw new VisitDayException();
        }
    }

    private void validateRangeVisitDay(final String visitDay) {
        int value = Integer.parseInt(visitDay);
        if (value < DECEMBER_START_DAY || value > DECEMBER_END_DAY) {
            throw new VisitDayException();
        }
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public Integer getVisitDay() {
        return visitDate.getDayOfMonth();
    }

    public boolean isBetweenDates(final LocalDate start, final LocalDate end) {
        return !visitDate.isBefore(start) && !visitDate.isAfter(end);
    }

    public boolean isWeekday() {
        return visitDate.getDayOfWeek() != DayOfWeek.FRIDAY && visitDate.getDayOfWeek() != DayOfWeek.SATURDAY;
    }

    public boolean isWeekend() {
        return visitDate.getDayOfWeek() == DayOfWeek.FRIDAY || visitDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isSunday() {
        return visitDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public boolean isChristmas() {
        return visitDate.equals(CHRISTMAS);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DecemberVisitDate decemberVisitDate1 = (DecemberVisitDate) o;

        return visitDate.equals(decemberVisitDate1.visitDate);
    }

    @Override
    public int hashCode() {
        return visitDate.hashCode();
    }
}