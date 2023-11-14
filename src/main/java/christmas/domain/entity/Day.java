package christmas.domain.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Day {

    private int day;

    private static final int PRESENT_YEAR = 2023;
    private static final int DECEMBER = 12;
    private static final int CHRISTMAS = 25;

    public Day(int day) {
        this.day = day;
    }

    DayOfWeek dayOfWeek = LocalDate.of(PRESENT_YEAR, DECEMBER, day).getDayOfWeek();

    private void validateIsNumber(int day){
        if(day>31||day<=0){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public boolean isWeekend(){
        return dayOfWeek==DayOfWeek.FRIDAY||dayOfWeek==DayOfWeek.SATURDAY;
    }

    public boolean isWeekday(){
        return dayOfWeek!=DayOfWeek.FRIDAY&&dayOfWeek!=DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay(){
        return dayOfWeek==DayOfWeek.SUNDAY||day==CHRISTMAS;
    }

    public boolean isBeforeDday(){
        return day<=25;
    }

}
