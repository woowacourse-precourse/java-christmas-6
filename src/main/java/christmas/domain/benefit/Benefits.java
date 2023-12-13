package christmas.domain.benefit;

import christmas.domain.menu.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.benefit.discount.DiscountFactory;
import christmas.domain.benefit.gifts.Gift;
import christmas.domain.benefit.gifts.GiftFactory;
import christmas.domain.menu.MenuAndCount;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Benefits {
    private final List<Benefit> benefits;

    public Benefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static Benefits from(PromotionDate visitDay, Orders orders){
        List<Benefit> benefits = new ArrayList<>();
        benefits.addAll(DiscountFactory.from(visitDay, orders));
        benefits.addAll(GiftFactory.from(visitDay, orders));
        return new Benefits(benefits);
    }

    public List<Benefit> getBenefits() {
        return Collections.unmodifiableList(benefits);
    }

    public boolean hasNoBenefits(){
        return benefits.isEmpty();
    }

    public int getTotalBenefitPrice(){
        return benefits.stream()
                .mapToInt(Benefit::getBenefitPrice)
                .sum();
    }

    public int calcDiscountPrice(){
        return benefits.stream()
                .filter(benefit -> benefit.isTypeOf(BenefitType.DISCOUNT))
                .mapToInt(Benefit::getBenefitPrice)
                .sum();
    }

    public boolean hasNoGift() {
        return benefits.stream()
                .noneMatch(benefit -> benefit.isTypeOf(BenefitType.GIFTS));
    }

    public List<MenuAndCount> getGifts(){
        return benefits.stream()
                .filter(benefit -> benefit.isTypeOf(BenefitType.GIFTS))
                .map(benefit -> (Gift) benefit)
                .map(Gift::getOrders)
                .flatMap(List::stream)
                .toList();
    }
}
