package christmas.order;

import christmas.enums.badge.Badge;
import christmas.enums.menu.MenuItem;

public record Receipt(Orders orders, Integer totalPriceBeforeDiscount, Integer totalBenefit, MenuItem gift, Badge badge) {
}
