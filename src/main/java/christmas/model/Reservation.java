package christmas.model;

import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Reservation {
    private final int PROMOTION_YEAR = 2023;
    private final int PROMOTION_MONTH = 12;
    private LocalDate reservedDate;
    private List<SimpleEntry<Menu, Integer>> orderedMenu;

    public Reservation(int date) {
        reservedDate = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, date);
    }

    public void orderMenu (List<SimpleEntry<Menu, Integer>> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }
}
