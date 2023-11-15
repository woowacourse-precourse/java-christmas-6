package christmas.dto;

import christmas.domain.Discount;
import christmas.type.DiscountType;

import java.util.HashMap;
import java.util.Map;

public class DiscountInfoDto {

    public static Map<String, Integer> of(Discount discount) {
        Map<String, Integer> discountMap = new HashMap<>();
        Map<String, Integer> discountValues = new HashMap<>();

        discountValues.put(DiscountType.CHRISTMAS_DISCOUNT.getDiscountText(), discount.getChristmasDiscount());
        discountValues.put(DiscountType.NORMAL_DISCOUNT.getDiscountText(), discount.getNormalDiscount());
        discountValues.put(DiscountType.WEEK_DISCOUNT.getDiscountText(), discount.getWeekDiscount());
        discountValues.put(DiscountType.SPECIAL_DISCOUNT.getDiscountText(), discount.getSpecialDiscount());
        discountValues.put(DiscountType.GIVING_DISCOUNT.getDiscountText(), discount.getGivingEventDiscount());

        for (Map.Entry<String, Integer> entry : discountValues.entrySet()) {
            if (entry.getValue() != 0) {
                discountMap.put(entry.getKey(), entry.getValue());
            }
        }
        return discountMap;
    }
}
