package christmas.utils;

import christmas.exceptions.IllegalDateFormatException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

public class StringToDateParser {

    public static LocalDate makeReservation(int year, int month, String input) {
        Integer date = StringToInteger(input);
        return toLocalDate(year, Month.of(month), date);
    }

    private static Integer StringToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalDateFormatException();
        }
    }

    private static LocalDate toLocalDate(int year, Month month, int date) {
        try {
            return LocalDate.of(year, month, date);
        } catch (DateTimeException e) {
            throw new IllegalDateFormatException();
        }
    }
}
