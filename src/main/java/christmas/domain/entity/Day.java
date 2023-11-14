package christmas.domain.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Day {

    private static final int PRESENT_YEAR = 2023;
    private static final int DECEMBER = 12;
    private static final int CHRISTMAS = 25;

    private static DayOfWeek getDayOfWeek(int day){
        return LocalDate.of(PRESENT_YEAR, DECEMBER, day).getDayOfWeek();
    }

    public static void validateIsDate(int day){
        if(day>31||day<=0){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static boolean isWeekend(int day){
        return getDayOfWeek(day)==DayOfWeek.FRIDAY||getDayOfWeek(day)==DayOfWeek.SATURDAY;
    }

    public static boolean isWeekday(int day){
        return getDayOfWeek(day)!=DayOfWeek.FRIDAY&&getDayOfWeek(day)!=DayOfWeek.SATURDAY;
    }

    public static boolean isSpecialDay(int day){
        return getDayOfWeek(day)==DayOfWeek.SUNDAY||day==CHRISTMAS;
    }

    public static boolean isBeforeDday(int day){
        return day<=25;
    }

}
