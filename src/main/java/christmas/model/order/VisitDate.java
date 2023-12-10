package christmas.model.order;

import static christmas.common.Constant.CHRISTMAS_DATE;
import static christmas.common.Constant.DATE_MAX_RANGE;
import static christmas.common.Constant.DATE_MIN_RANGE;
import static christmas.common.Constant.DATE_MONTH;
import static christmas.common.Constant.DATE_YEAR;

import christmas.util.validator.IntegerValidator;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class VisitDate {
    private final LocalDate visitDate;

    private VisitDate(int visitDay) {
        validate(visitDay);
        this.visitDate = LocalDate.of(DATE_YEAR, DATE_MONTH, visitDay);
    }

    public static VisitDate of(int day) {
        return new VisitDate(day);
    }

    private void validate(int day) {
        IntegerValidator.validateRange(day, DATE_MIN_RANGE, DATE_MAX_RANGE);
    }

    public boolean isDateInPeriod(LocalDate firstDate, LocalDate lastDate) {
        return !visitDate.isBefore(firstDate) && !visitDate.isAfter(lastDate);
    }

    public boolean equals(LocalDate date) {
        return visitDate.equals(date);
    }

    public boolean isWeekend() {
        DayOfWeek dayOfWeek = visitDate.getDayOfWeek();

        return dayOfWeek.equals(DayOfWeek.FRIDAY) || dayOfWeek.equals(DayOfWeek.SATURDAY);
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isSpecialDay() {
        return isSunday() || equals(CHRISTMAS_DATE);
    }

    private boolean isSunday() {
        return visitDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public int getMonth() {
        return visitDate.getMonthValue();
    }

    public int getDay() {
        return visitDate.getDayOfMonth();
    }
}
