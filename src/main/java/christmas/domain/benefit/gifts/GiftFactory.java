package christmas.domain.benefit.gifts;

import christmas.domain.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAndCount;
import christmas.exception.PromotionExceptionMaker;
import java.util.Arrays;
import java.util.List;

public enum GiftFactory {

    FREE_DESSERT("증정 이벤트",
            List.of(new MenuAndCount(Menu.CHAMPAGNE, 1))) {
        @Override
        boolean canApply(PromotionDate promotionDate, Orders orders) {
            return orders.getTotalPrice() >= 120_000;
        }
    };
    private final String benefitName;
    private final List<MenuAndCount> menuAndCount;

    GiftFactory(String benefitName, List<MenuAndCount> menuAndCount) {
        this.benefitName = benefitName;
        this.menuAndCount = menuAndCount;
    }
    abstract boolean canApply(PromotionDate promotionDate, Orders orders);

    public static List<Gift> from(PromotionDate promotionDate, Orders orders) {
        return Arrays.stream(values())
                .filter(factory -> factory.canApply(promotionDate, orders))
                .map(factory -> factory.create(promotionDate, orders))
                .toList();
    }

    public Gift create(PromotionDate promotionDate, Orders orders) {
        if (!canApply(promotionDate, orders)) {
            throw PromotionExceptionMaker.CANT_APPLY_BENEFIT_EXCEPTION.makeException();
        }
        return new Gift(benefitName, new Orders(menuAndCount));
    }
}
