package christmas.domain.benefit;

import christmas.domain.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.menu.Category;
import christmas.domain.menu.Menu;
import christmas.exception.PromotionExceptionMaker;
import java.util.Arrays;
import java.util.List;

public enum BenefitFactory {
    D_DAY(BenefitType.DISCOUNT, "크리스마스 디데이 할인") {
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isBefore(PromotionDate.CHRISTMAS)
                    || promotionDate.isSame(PromotionDate.CHRISTMAS);
        }

        @Override
        int getBenefitPrice(PromotionDate promotionDate, Orders orders) {
            return 1000 + promotionDate.getAfterDate(PromotionDate.FIRST_DAY) * 100;
        }
    }, WEEKDAY_DISCOUNT(BenefitType.DISCOUNT, "평일 할인"){
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isWeekDay();
        }

        @Override
        int getBenefitPrice(PromotionDate promotionDate, Orders orders) {
            return orders.getMenuCountOf(Category.DESSERT) * 2_023;
        }
    },
    WEEKEND_DISCOUNT(BenefitType.DISCOUNT, "주말 할인"){
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isWeekend();
        }

        @Override
        int getBenefitPrice(PromotionDate promotionDate, Orders orders) {
            return orders.getMenuCountOf(Category.MAIN_COURSE) * 2_023;
        }
    },
    SPECIAL_DISCOUNT(BenefitType.DISCOUNT, "특별 할인"){
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return promotionDate.isSpecialDay();
        }

        @Override
        int getBenefitPrice(PromotionDate promotionDate, Orders orders) {
            return 1000;
        }
    },

    //todo 증정 분리하기 - 증정 메뉴도 필드로 가져야함
    FREE_DESSERT(BenefitType.GIFTS, "증정 이벤트"){
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return orders.getTotalPrice() >= 120_000;
        }

        @Override
        int getBenefitPrice(PromotionDate promotionDate, Orders orders) {
            return Menu.CHAMPAGNE.getPrice();
        }
    };

    private final BenefitType benefitType;
    private final String benefitName;

    BenefitFactory(BenefitType benefitType, String benefitName) {
        this.benefitType = benefitType;
        this.benefitName = benefitName;
    }

    abstract boolean canApply(PromotionDate promotionDate, Orders orders);

    abstract int getBenefitPrice(PromotionDate promotionDate, Orders orders);

    public static List<Benefit> allPossibleBenefit(PromotionDate promotionDate, Orders orders) {
        return Arrays.stream(values())
                .filter(benefitFactory -> benefitFactory.canApply(promotionDate, orders))
                .map(benefitFactory -> benefitFactory.create(promotionDate, orders))
                .toList();
    }

    public Benefit create(PromotionDate promotionDate, Orders orders) {
        if (!canApply(promotionDate, orders)) {
            throw PromotionExceptionMaker.CANT_APPLY_BENEFIT_EXCEPTION.makeException();
        }
        int benefitPrice = getBenefitPrice(promotionDate, orders);
        return new Benefit(benefitType, benefitName, benefitPrice);
    }


}
