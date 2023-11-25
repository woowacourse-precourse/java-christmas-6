package christmas.domain;

public class ReservationDate {
    static final int FIRST_DAY_OF_DECEMBER = 1;
    static final int LAST_DAY_OF_DECEMBER = 31;

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
}
