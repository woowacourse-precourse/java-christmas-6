package christmas.dto;

import christmas.domain.Discount;
import christmas.type.DiscountType;

import java.util.HashMap;
import java.util.Map;

public class DiscountInfoDto {

    public static Map<String, Integer> of(Discount discount) {
        Map<String, Integer> discountMap = new HashMap<>();
        discountMap.put(DiscountType.CHRISTMAS_DISCOUNT.getDiscountText(), discount.getChristmasDiscount());
        discountMap.put(DiscountType.NORMAL_DISCOUNT.getDiscountText(), discount.getNormalDiscount());
        discountMap.put(DiscountType.SPECIAL_DISCOUNT.getDiscountText(), discount.getSpecialDiscount());
        discountMap.put(DiscountType.GIVING_DISCOUNT.getDiscountText(), discount.getGivingEventDiscount());
        return discountMap;
    }
}
