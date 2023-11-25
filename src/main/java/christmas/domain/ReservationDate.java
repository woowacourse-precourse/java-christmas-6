package christmas.domain;

public class ReservationDate {
    private final int date;

    public ReservationDate(int date){
        validateReservationDate(date);
        this.date = date;
    }

    private void validateReservationDate(int date) throws IllegalArgumentException{
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException();
        }
    }
}
