package christmas.event;

import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.enums.menu.MenuItem;
import java.util.List;

public record EventBenefit(List<EventResult> eventResults, MenuItem gift) {
    public Integer showDiscountBenefits(){
        Integer discountBenefits = 0;
        for (EventResult eventResult : eventResults) {
            discountBenefits += eventResult.discountBenefit();
        }
        return discountBenefits+gift.getAmount();
    }

    public static EventBenefit NO_EVENT_BENEFIT(){
        return new EventBenefit(List.of(EventResult.NO_EVENT_RESULT()), NO_MENU);
    }
}
