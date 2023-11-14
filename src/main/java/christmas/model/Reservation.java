package christmas.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Reservation {
    public static final int PROMOTION_YEAR = 2023;
    public static final int PROMOTION_MONTH = 12;

    private LocalDate reservedDate;
    private List<SimpleEntry<Menu, Integer>> orderedMenu;
    private boolean isSpecialPromotionDate = false;
    private int sumOfMenu = 0;

    public Reservation(int date) {
        reservedDate = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, date);

        if (reservedDate.getDayOfWeek() == DayOfWeek.SUNDAY || date == 25) {
            isSpecialPromotionDate = true;
        }
    }

    public void orderMenu (List<SimpleEntry<Menu, Integer>> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

}
