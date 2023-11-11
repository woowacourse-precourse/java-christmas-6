package christmas.order;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;

import christmas.enums.menu.MenuItem;
import christmas.manangers.MenuManager;

public class OrderMenu {
    private final MenuItem menuItem;
    private final Integer orderQuantity;

    public OrderMenu(MenuItem menuItem, Integer orderQuantity) {
        this.menuItem = menuItem;
        this.orderQuantity = orderQuantity;
    }

    public static OrderMenu createOrderMenu(String menuName, Integer orderQuantity) {
        MenuItem menuByName = MenuManager.getMenuByName(menuName);
        return new OrderMenu(menuByName, orderQuantity);
    }

    public Integer findEventMenuCount(MenuItem[] eventMenus) {
        for (MenuItem eventMenu : eventMenus) {
            if(this.menuItem.equals(eventMenu)){
                return orderQuantity;
            }
        }
        return NO_BENEFIT.getAmount();
    }

    public Integer calculatePrice() {
        return this.menuItem.getPrice() * this.orderQuantity;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
