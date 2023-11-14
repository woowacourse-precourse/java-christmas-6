package christmas.model;

import christmas.controller.Promotion;

import java.time.LocalDate;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Reservation {
    public static final int PROMOTION_YEAR = 2023;
    public static final int PROMOTION_MONTH = 12;
    public static final LocalDate PROMOTION_DEFAULT_START = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, 1);
    public static final LocalDate PROMOTION_DEFAULT_END = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, 31);

    private LocalDate reservedDate;
    private List<SimpleEntry<Menu, Integer>> orderedMenu;
    private boolean isSpecialPromotionDate = false;
    private int totalAmount = 0;
    private Map<Promotion, Integer> eventApplied = new TreeMap<>();
    private int discountedPrice = 0;

    public Reservation(int date) {
        reservedDate = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, date);
    }

    public void orderMenu (List<SimpleEntry<Menu, Integer>> orderedMenu) {
        this.orderedMenu = orderedMenu;
        totalAmount = Menu.checkBill(orderedMenu);
    }

    public int checkBill () {
        return totalAmount;
    }

    public boolean isIncludedPromotionPeriod (Promotion promotion) {
        return promotion.isInPromotionPeriod(reservedDate);
    }

    public boolean isIncludedPresentPromotion () {
        return eventApplied.containsKey(Promotion.PRESENT_PROMOTION);
    }

}
