package christmas;

import christmas.enums.MainMenu;
import christmas.enums.MenuItem;

public class OneOrderMenu {
    private final MenuItem menuItem;
    private final Integer orderQuantity;

    public OneOrderMenu(MenuItem menuItem, Integer orderQuantity) {
        this.menuItem = menuItem;
        this.orderQuantity = orderQuantity;
    }

   /* public OneOrderMenu createOrder(MainMenu mainMenu, Integer orderQuantity){
        return new OneOrderMenu(menuItem,orderQuantity);
    }*/
    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
