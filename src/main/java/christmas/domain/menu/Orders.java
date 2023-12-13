package christmas.domain.menu;

import christmas.exception.PromotionExceptionMaker;
import java.util.List;

public class Orders {
    private final List<MenuAndCount> orders;

    public Orders(List<MenuAndCount> orders) {
        validateOrders(orders);
        this.orders = orders;
    }

    private void validateOrders(List<MenuAndCount> orders) {
        if (orders.isEmpty()) {
            throw PromotionExceptionMaker.EMPTY_ORDER.makeException();
        }
        validateDuplicateMenu(orders);
        validateOrderNumber(orders);
        validateOrderNotOnlyDrink(orders);

    }

    public static Orders from(List<String> orders) {
        return new Orders(orders.stream()
                .map(MenuAndCount::from)
                .toList());
    }

    public List<MenuAndCount> getOrders() {
        return orders;
    }

    public int getTotalPrice() {
        return orders.stream()
                .mapToInt(MenuAndCount::calcPrice)
                .sum();
    }

    public boolean hasCategoryOf(Category category) {
        return orders.stream()
                .anyMatch(menuAndCount -> menuAndCount.isCategory(category));
    }

    public int getMenuCountOf(Category category) {
        return orders.stream()
                .filter(menuAndCount -> menuAndCount.isCategory(category))
                .mapToInt(MenuAndCount::getCount)
                .sum();
    }

    private void validateOrderNotOnlyDrink(List<MenuAndCount> orders) {
        if (isAllDrink(orders)) {
            throw PromotionExceptionMaker.ALL_ORDER_DRINK.makeException();
        }
    }

    private boolean isAllDrink(List<MenuAndCount> orders) {
        return orders.stream()
                .allMatch(menuAndCount -> menuAndCount.isCategory(Category.BEVERAGE));
    }

    private void validateDuplicateMenu(List<MenuAndCount> orders) {
        int distinctMenuCount = (int) orders.stream()
                .map(MenuAndCount::getMenuName)
                .distinct()
                .count();
        if (distinctMenuCount != orders.size()) {
            throw PromotionExceptionMaker.DUPLICATE_ORDER.makeException();
        }
    }

    private void validateOrderNumber(List<MenuAndCount> orders) {
        int orderNumber = orders.stream()
                .mapToInt(MenuAndCount::getCount)
                .sum();
        if (orderNumber > 20) {
            throw PromotionExceptionMaker.TOO_MANY_ORDERS.makeException();
        }
    }
}
