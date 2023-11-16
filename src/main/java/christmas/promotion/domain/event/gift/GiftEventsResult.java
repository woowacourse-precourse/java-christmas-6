package christmas.promotion.domain.event.gift;

import christmas.promotion.domain.event.Event;
import christmas.promotion.domain.menu.Menu;
import christmas.promotion.vo.Price;
import christmas.promotion.vo.Quantity;

import java.util.Collections;
import java.util.Map;

public class GiftEventsResult {
    private final Map<Event, Price> giftEventBenefits;
    private final Map<Menu, Quantity> giftMenus;

    public GiftEventsResult(final Map<Event, Price> giftEventBenefits, final Map<Menu, Quantity> giftMenus) {
        this.giftEventBenefits = Collections.unmodifiableMap(giftEventBenefits);
        this.giftMenus = Collections.unmodifiableMap(giftMenus);
    }

    public Map<Event, Price> getGiftEventBenefits() {
        return giftEventBenefits;
    }

    public Map<Menu, Quantity> getGiftMenus() {
        return giftMenus;
    }
}
