package christmas;

import christmas.enums.MainMenu;
import christmas.enums.MenuItem;
import java.awt.Menu;

public class OrderMenu {
    private final MenuItem menuItem;
    private final Integer orderQuantity;

    public OrderMenu(MenuItem menuItem, Integer orderQuantity) {
        this.menuItem = menuItem;
        this.orderQuantity = orderQuantity;
    }

    public static OrderMenu createOrderMenu(String menuName, Integer orderQuantity){
        MenuItem byName = MenuItem.getByName(menuName);
        return new OrderMenu(byName,orderQuantity);
    }

    public Integer calculatePrice(){
       return this.menuItem.getPrice() * this.orderQuantity;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
