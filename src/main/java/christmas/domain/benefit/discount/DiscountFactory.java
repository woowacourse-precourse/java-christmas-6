package christmas.domain.benefit.discount;

import christmas.domain.menu.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.menu.Category;
import christmas.exception.PromotionExceptionMaker;
import java.util.Arrays;
import java.util.List;

public enum DiscountFactory {
    D_DAY("크리스마스 디데이 할인") {
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isBefore(PromotionDate.CHRISTMAS)
                    || promotionDate.isSame(PromotionDate.CHRISTMAS);
        }

        @Override
        int getDiscountPrice(PromotionDate promotionDate, Orders orders) {
            return D_DAY_START_PRICE + promotionDate.getAfterDate(PromotionDate.FIRST_DAY) * D_DAY_INCRESEMENT_PRICE;
        }
    }, WEEKDAY_DISCOUNT("평일 할인"){
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isWeekDay()
                    && orders.hasCategoryOf(Category.DESSERT);
        }

        @Override
        int getDiscountPrice(PromotionDate promotionDate, Orders orders) {
            return orders.getMenuCountOf(Category.DESSERT) * WEEK_DISCOUNT;
        }
    },
    WEEKEND_DISCOUNT("주말 할인"){
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isWeekend()
                    && orders.hasCategoryOf(Category.MAIN_COURSE);
        }

        @Override
        int getDiscountPrice(PromotionDate promotionDate, Orders orders) {
            return orders.getMenuCountOf(Category.MAIN_COURSE) * WEEK_DISCOUNT;
        }
    },
    SPECIAL_DISCOUNT("특별 할인"){
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isSpecialDay();
        }

        @Override
        int getDiscountPrice(PromotionDate promotionDate, Orders orders) {
            return SPECIAL_DISCOUNT_PRICE;
        }
    };

    public static final int D_DAY_START_PRICE = 1000;
    public static final int D_DAY_INCRESEMENT_PRICE = 100;
    public static final int SPECIAL_DISCOUNT_PRICE = 1000;
    public static final int WEEK_DISCOUNT = 2_023;
    private final String discountName;

    DiscountFactory(String discountName) {
        this.discountName = discountName;
    }

    abstract boolean canApply(PromotionDate promotionDate, Orders orders);

    abstract int getDiscountPrice(PromotionDate promotionDate, Orders orders);

    public static List<Discount> from(PromotionDate promotionDate, Orders orders) {
        return Arrays.stream(values())
                .filter(factory -> factory.canApply(promotionDate, orders))
                .map(factory -> factory.create(promotionDate, orders))
                .toList();
    }

    public Discount create(PromotionDate promotionDate, Orders orders) {
        if (!canApply(promotionDate, orders)) {
            throw PromotionExceptionMaker.CANT_APPLY_BENEFIT_EXCEPTION.makeException();
        }
        int discountPrice = getDiscountPrice(promotionDate, orders);
        return new Discount(discountName, discountPrice);
    }


}
