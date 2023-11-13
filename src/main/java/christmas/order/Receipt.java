package christmas.order;

import christmas.enums.badge.Badge;
import christmas.enums.menu.MenuItem;
import christmas.enums.menu.NoMenu;
import christmas.event.OneEventResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record Receipt(List<OneEventResult> oneEventResults, Integer totalPriceBeforeDiscount, Integer discountBenefit, MenuItem gift, Badge badge) {
    public Receipt(List<OneEventResult> oneEventResults, Integer totalPriceBeforeDiscount, Integer discountBenefit,
                   MenuItem gift, Badge badge) {
        this.totalPriceBeforeDiscount = totalPriceBeforeDiscount;
        this.discountBenefit = discountBenefit;
        this.gift = gift;
        if(!gift.isNone()){
            oneEventResults = new ArrayList<>(oneEventResults);
            oneEventResults.add(new OneEventResult(gift().getName(), gift.getAmount()));
            oneEventResults.stream().collect(Collectors.toUnmodifiableList());
        }
        this.oneEventResults = oneEventResults;
        this.badge = badge;
    }
}
