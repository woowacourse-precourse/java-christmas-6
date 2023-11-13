package christmas.event;

import christmas.enums.menu.MenuItem;
import christmas.enums.menu.NoMenu;

public record Gift(MenuItem menuItem, Integer quantity) {
    private final static Integer NO_QUANTITY = 0;

    public static Gift NO_GIFT() {
        return new Gift(NoMenu.NO_MENU, NO_QUANTITY);
    }

    public Integer getGiftDiscountBenefit() {
        return menuItem.getAmount() * quantity;
    }
}
