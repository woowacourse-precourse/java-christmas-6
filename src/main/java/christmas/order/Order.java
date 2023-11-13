package christmas.order;

import static christmas.enums.benefit.DiscountBenefit.NO_BENEFIT;

import christmas.enums.menu.MenuItem;
import christmas.utils.MenuList;
import java.util.Objects;

public class Order {
    private final MenuItem menuItem;
    private final Integer orderQuantity;

    public Order(MenuItem menuItem, Integer orderQuantity) {
        this.menuItem = menuItem;
        this.orderQuantity = orderQuantity;
    }

    public static Order createOrderMenu(String menuName, Integer orderQuantity) {
        MenuItem menuByName = MenuList.getMenuByName(menuName);
        return new Order(menuByName, orderQuantity);
    }

    public Integer findEventMenuCount(MenuItem[] eventMenus) {
        for (MenuItem eventMenu : eventMenus) {
            if (this.menuItem.equals(eventMenu)) {
                return orderQuantity;
            }
        }
        return NO_BENEFIT.getAmount();
    }

    public Integer calculatePrice() {
        return this.menuItem.getAmount() * this.orderQuantity;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(menuItem, order.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItem);
    }
}
