package christmas;

import christmas.enums.MenuItem;

public class OrderMenu {
    private final MenuItem menuItem;
    private final Integer orderQuantity;

    public OrderMenu(MenuItem menuItem, Integer orderQuantity) {
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
