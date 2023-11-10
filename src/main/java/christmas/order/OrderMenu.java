package christmas.order;

import christmas.enums.menu.MenuItem;

public class OrderMenu {
    private final MenuItem menuItem;
    private final Integer orderQuantity;

    public OrderMenu(MenuItem menuItem, Integer orderQuantity) {
        this.menuItem = menuItem;
        this.orderQuantity = orderQuantity;
    }

    public static OrderMenu createOrderMenu(String menuName, Integer orderQuantity) {
        MenuItem menuByName = MenuList.getMenuByName(menuName);
        return new OrderMenu(menuByName, orderQuantity);
    }

    public Integer findEventMenuCount(MenuItem eventMenu) {
        if(this.menuItem.equals(eventMenu)){
            return orderQuantity;
        }
        return 0;
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
