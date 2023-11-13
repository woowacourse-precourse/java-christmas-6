package christmas.event;

import java.util.List;

public record EventBenefit(List<OneEventResult> oneEventResults, Gift gift) {
    public Integer showTotalDiscount() {
        Integer discountBenefits = 0;
        for (OneEventResult oneEventResult : oneEventResults) {
            discountBenefits += oneEventResult.discountBenefit();
        }
        return discountBenefits + gift.getGiftDiscountBenefit();
    }

    public EventBenefit(List<OneEventResult> oneEventResults, Gift gift) {
        this.oneEventResults = oneEventResults.stream().filter(eventResult -> !eventResult.isNone()).toList();
        this.gift = gift;
    }

    public static EventBenefit NO_EVENT_BENEFIT() {
        return new EventBenefit(List.of(OneEventResult.NO_EVENT_RESULT()), Gift.NO_GIFT());
    }
}
