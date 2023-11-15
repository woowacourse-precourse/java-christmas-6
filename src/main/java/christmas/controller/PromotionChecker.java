package christmas.controller;

import christmas.model.Menu;
import christmas.model.Promotion;
import christmas.model.Reservation;

import java.time.DayOfWeek;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PromotionChecker {
    public static final int MIN_TOTAL_TO_APPLY_PROMOTION = 10_000;
    private static final DayOfWeek[] WEEKDAYS = {DayOfWeek.SUNDAY, DayOfWeek.MONDAY,DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,DayOfWeek.TUESDAY};
    private static final DayOfWeek[] WEEKENDS = {DayOfWeek.FRIDAY, DayOfWeek.SATURDAY};

    private static final  String WEEKDAY_PROMOTION_CATEGORY = "디저트";
    private static final String WEEKEND_PROMOTION_CATEGORY = "메인";

    public boolean canPromotionAppliable(Reservation reservation) {
        return reservation.checkBill() >= MIN_TOTAL_TO_APPLY_PROMOTION;
    }

    public Map<Promotion, Integer> applyPromotion (Reservation reservation) {
        Map<Promotion, Integer> promotionApplied = new HashMap<>();

        checkChristmasPromotionAppliable(reservation, promotionApplied);
        checkWeekdayPromotionAppliable(reservation, promotionApplied);
        checkWeekendPromotionAppliable(reservation, promotionApplied);
        checkSpecialPromotionAppliable(reservation, promotionApplied);
        checkPresentPromotion(reservation, promotionApplied);

        return promotionApplied;
    }

    private  void checkPresentPromotion(Reservation reservation,  Map<Promotion, Integer> promotionApplied) {
        if (reservation.checkBill() >= 120_000) {
            int benefit = Promotion.PRESENT_PROMOTION.checkPromotionDefaultBenefit();
            promotionApplied.put(Promotion.PRESENT_PROMOTION, benefit);
        }
    }
    private void checkSpecialPromotionAppliable(Reservation reservation, Map<Promotion, Integer> promotionApplied) {
        if (reservation.checkReservedDate().getDayOfMonth() == 25 || reservation.checkReservedDate().getDayOfWeek() == DayOfWeek.SUNDAY) {
            promotionApplied.put(Promotion.SPECIAL_PROMOTION, Promotion.SPECIAL_PROMOTION.checkPromotionDefaultBenefit());
        }
    }

    private void checkWeekendPromotionAppliable(Reservation reservation, Map<Promotion, Integer> promotionApplied) {
        if (Arrays.stream(WEEKENDS).toList().contains(
                reservation.checkReservedDate().getDayOfWeek()
        )) {
            List<SimpleEntry<Menu, Integer>> menuPromotionApplied = reservation.checkMenuByCategory(WEEKEND_PROMOTION_CATEGORY);

            int matchedMenuByPromotionCategory = menuPromotionApplied.stream().mapToInt(SimpleEntry::getValue).sum();
            int totalBenefit = matchedMenuByPromotionCategory * Promotion.WEEKEND_PROMOTION.checkPromotionDefaultBenefit();

            promotionApplied.put(Promotion.WEEKEND_PROMOTION, totalBenefit);
        }
    }

    private void checkWeekdayPromotionAppliable(Reservation reservation, Map<Promotion, Integer> promotionApplied) {
        if (Arrays.stream(WEEKDAYS).toList()
                .contains(reservation.checkReservedDate().getDayOfWeek())) {
            List<SimpleEntry<Menu, Integer>> menuPromotionApplied = reservation.checkMenuByCategory(WEEKDAY_PROMOTION_CATEGORY);

            int matchedMenuByPromotionCategory = menuPromotionApplied.stream().mapToInt(SimpleEntry::getValue).sum();
            int totalBenefit = matchedMenuByPromotionCategory * Promotion.WEEKDAY_PROMOTION.checkPromotionDefaultBenefit();

            promotionApplied.put(Promotion.WEEKDAY_PROMOTION, totalBenefit);
        }
    }

    private void checkChristmasPromotionAppliable (Reservation reservation, Map<Promotion, Integer> promotionApplied) {
        if (reservation.isIncludedPromotionPeriod(Promotion.CHRISTMAS_DDAY_PROMOTION)) {
            int interval = reservation.checkReservedDate().getDayOfMonth() - 1;
            int christmasPromotionBenefit = Promotion.CHRISTMAS_DDAY_PROMOTION.checkPromotionDefaultBenefit() + interval * 100;

            promotionApplied.put(Promotion.CHRISTMAS_DDAY_PROMOTION, christmasPromotionBenefit);
        }
    }
}
