package christmas.service;

import christmas.domain.date.Date;

public class EventService {

    private Date date;

    public void initDate(final int date) {
        this.date = new Date(date);
    }
}
