package christmas.order;

import christmas.enums.badge.Badge;
import christmas.enums.menu.MenuItem;
import christmas.event.EventResult;
import java.util.List;

public record Receipt(List<EventResult>eventResults, Integer totalPriceBeforeDiscount, Integer discountBenefit, MenuItem gift, Badge badge) {
}
