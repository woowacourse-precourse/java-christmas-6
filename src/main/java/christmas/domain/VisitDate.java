package christmas.domain;

import java.time.LocalDate;

public class VisitDate {
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private final LocalDate visitDate;

    public VisitDate(int visitDate) {
        this.visitDate = LocalDate.of(YEAR, MONTH, visitDate);
    }
}
