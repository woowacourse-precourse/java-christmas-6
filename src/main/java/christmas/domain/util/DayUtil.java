package christmas.domain.util;

import christmas.domain.constants.DateEnum;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DayUtil {



    private static DayOfWeek getDayOfWeek(int day){
        return LocalDate.of(
                DateEnum.Date.PRESENT_YEAR.getValue(),
                DateEnum.Date.DECEMBER.getValue(),
                day).getDayOfWeek();
    }

    public static void validateIsDate(int day){
        if(day>DateEnum.Date.LAST_DAY_OF_MONTH.getValue()||day<=0){
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
        return getDayOfWeek(day)==DayOfWeek.SUNDAY||day==DateEnum.Date.DATE_OF_CHRISTMAS.getValue();
    }

    public static boolean isBeforeDday(int day){
        return day<=DateEnum.Date.DATE_OF_CHRISTMAS.getValue();
    }

}
