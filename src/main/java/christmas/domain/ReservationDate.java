package christmas.domain;

public class ReservationDate {
    static final int DATE_OF_CHRISTMAS = 25;

    static final int FIRST_DAY_OF_DECEMBER = 1;
    static final int LAST_DAY_OF_DECEMBER = 31;
    static final int DATE_OF_FIRST_FRIDAY = 1;
    static final int DATE_OF_FIRST_SATURDAY = 2;
    static final int WEEK = 7;

    private final int date;

    public ReservationDate(int date){
        validateReservationDate(date);
        this.date = date;
    }

    private void validateReservationDate(int date) throws IllegalArgumentException{
        if(date < FIRST_DAY_OF_DECEMBER || date > LAST_DAY_OF_DECEMBER) {
            throw new IllegalArgumentException();
        }
    }

    public int christmasCountdown() {
        return DATE_OF_CHRISTMAS - getDate();
    }

    private int getDate() {
        return date;
    }

    public boolean isWeekDay() {
        boolean isWeekDay = true;
        if(date % WEEK == DATE_OF_FIRST_FRIDAY || date % WEEK == DATE_OF_FIRST_SATURDAY) {
            isWeekDay = false;
        }

        return isWeekDay;
    }
}
