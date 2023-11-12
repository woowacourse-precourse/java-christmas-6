package christmas.order;

import christmas.enums.badge.Badge;
import christmas.enums.menu.MenuItem;
import christmas.event.OneEventResult;
import java.util.List;

public record Receipt(List<OneEventResult> oneEventResults, Integer totalPriceBeforeDiscount, Integer discountBenefit, MenuItem gift, Badge badge) {
}
