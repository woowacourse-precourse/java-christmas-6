package christmas.domain;

import christmas.exception.PromotionExceptionMaker;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class PromotionDate {
    public static final PromotionDate FIRST_DAY = new PromotionDate(1);
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    public static final PromotionDate CHRISTMAS = new PromotionDate(25);
    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private final LocalDate visitDate;


    public PromotionDate(int visitDate) {
        try{
            this.visitDate = LocalDate.of(YEAR, MONTH, visitDate);
        } catch (DateTimeException e){
            throw PromotionExceptionMaker.INVALID_DATE.makeException();
        }
    }

    public int getDayOfMonth() {
        return visitDate.getDayOfMonth();
    }

    public int getRemainingDays(PromotionDate promotionDate) {
        return promotionDate.getDayOfMonth() - this.getDayOfMonth();
    }

    public boolean isBefore(PromotionDate promotionDate) {
        return this.visitDate.isBefore(promotionDate.visitDate);
    }

    public boolean isSame(PromotionDate promotionDate) {
        return this.visitDate.isEqual(promotionDate.visitDate);
    }


    public boolean isWeekend() {
        DayOfWeek week = visitDate.getDayOfWeek();
        return week == DayOfWeek.FRIDAY || week == DayOfWeek.SATURDAY;
    }

    public boolean isWeekDay() {
        return !isWeekend();
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(this.getDayOfMonth());
    }

    public int getAfterDate(PromotionDate firstDay) {
        return this.getDayOfMonth() - firstDay.getDayOfMonth();
    }
}
