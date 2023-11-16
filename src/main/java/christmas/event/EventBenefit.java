package christmas.event;

import java.util.List;

public record EventBenefit(List<OneEventResult> oneEventResults, Gift gift) {
    public EventBenefit(List<OneEventResult> oneEventResults, Gift gift) {
        this.oneEventResults = oneEventResults.stream().filter(eventResult -> !eventResult.isNone()).toList();
        this.gift = gift;
    }

    public Integer showTotalDiscount() {
        Integer discountBenefits = 0;
        for (OneEventResult oneEventResult : oneEventResults) {
            discountBenefits += oneEventResult.discountBenefit();
        }
        return discountBenefits + gift.getGiftDiscountBenefit();
    }
}
