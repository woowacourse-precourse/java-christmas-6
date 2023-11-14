package christmas.domain.date;

import christmas.domain.date.validator.DateValidator;

public class Date {

    private final int date;

    public Date(final int date) {
        DateValidator.validateDate(date);
        this.date = date;
    }

    public int getDate() {
        return this.date;
    }
}
