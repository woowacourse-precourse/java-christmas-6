package christmas.order;

import static christmas.enums.benefit.DiscountBenefit.MINIMUM_REQUIRE_AMOUNT;

import christmas.enums.badge.Badge;
import christmas.event.Gift;
import christmas.event.OneEventResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Receipt(List<OneEventResult> oneEventResults, Integer totalPriceBeforeDiscount, Integer discountBenefit,
                      Gift gift, Badge badge) {

    public Receipt(List<OneEventResult> oneEventResults, Integer totalPriceBeforeDiscount, Integer discountBenefit,
                   Gift gift, Badge badge) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.discountBenefit = discountBenefit;
        this.gift = gift;
        if (!gift.isNone()) {
            oneEventResults = new ArrayList<>(oneEventResults);
            oneEventResults.add(new OneEventResult(gift().getName(), gift.getGiftDiscountBenefit()));
            oneEventResults.stream().collect(Collectors.toUnmodifiableList());
        }
        this.oneEventResults = oneEventResults;
        this.badge = badge;
    }

    public Boolean isEligible() {
        return totalPriceBeforeDiscount > MINIMUM_REQUIRE_AMOUNT.getAmount();
    }

    public Boolean isNoGift() {
        return gift.isNone();
    }

}
