package christmas.event;

import static christmas.enums.events.NoEvent.NO_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.enums.menu.MenuItem;
import java.util.List;

public record EventBenefit(List<OneEventResult> oneEventResults, MenuItem gift) {
    public Integer showTotalDiscount(){
        Integer discountBenefits = 0;
        for (OneEventResult oneEventResult : oneEventResults) {
            discountBenefits += oneEventResult.discountBenefit();
        }
        return discountBenefits+gift.getAmount();
    }

    public EventBenefit(List<OneEventResult> oneEventResults, MenuItem gift) {
        this.oneEventResults = oneEventResults.stream().filter(eventResult -> !eventResult.isNone()).toList();
        this.gift = gift;
    }

    public static EventBenefit NO_EVENT_BENEFIT(){
        return new EventBenefit(List.of(OneEventResult.NO_EVENT_RESULT()), NO_MENU);
    }
}
