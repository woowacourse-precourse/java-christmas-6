package christmas.model;

import christmas.controller.PromotionChecker;

import java.time.LocalDate;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class Reservation {
    public static final int PROMOTION_YEAR = 2023;
    public static final int PROMOTION_MONTH = 12;
    public static final LocalDate PROMOTION_DEFAULT_START = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, 1);
    public static final LocalDate PROMOTION_DEFAULT_END = LocalDate.of(PROMOTION_YEAR, PROMOTION_MONTH, 31);

    private PromotionChecker checker = new PromotionChecker();
    private LocalDate reservedDate;
    private List<SimpleEntry<Menu, Integer>> orderedMenu;
    private int totalAmount = 0;
    private Map<Promotion, Integer> promotionApplied;
    private EventBadge eventBadge;

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

    public int checkAppliedPromotionBenefit () {
        if (promotionApplied.isEmpty()) {
            return 0;
        }

        return promotionApplied.values()
                .stream()
                .reduce(0, (a, b) -> a+b);
    }

    public void applyPromotion () {
        if (!checker.canPromotionAppliable(this)) {
            this.promotionApplied = new HashMap<>();
            return;
        }
        this.promotionApplied = checker.applyPromotion(this);
        this.eventBadge = EventBadge.giveEventBadge(checkAppliedPromotionBenefit());
    }

    public boolean isIncludedPromotionPeriod (Promotion promotion) {
        return promotion.isInPromotionPeriod(reservedDate);
    }

    public LocalDate checkReservedDate () {
        return reservedDate;
    }

    public List<SimpleEntry<Menu, Integer>> checkMenuByCategory (String category) {
        return orderedMenu.stream()
                .filter(el -> el.getKey().checkCategory(category))
                .toList();
    }


    public boolean checkPromotionContained (Promotion promotion) {
        return promotionApplied.containsKey(promotion);
    }

    public List<SimpleEntry<Menu, Integer>> checkOrderedMenu () {
        return orderedMenu;
    }

    public Map<Promotion, Integer> checkPromotionApplied () {
        return promotionApplied;
    }

    public EventBadge checkEventBadge ( ) {
        return eventBadge;
    }
}
