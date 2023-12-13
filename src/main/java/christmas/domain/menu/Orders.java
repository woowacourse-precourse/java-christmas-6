package christmas.domain.menu;

import christmas.exception.PromotionExceptionMaker;
import java.util.List;

public class Orders {
    private final List<MenuAndCount> orders;

    public Orders(List<MenuAndCount> orders) {
        this.orders = orders;
    }

    private static void validateOrders(List<MenuAndCount> orders) {
        validateDuplicateMenu(orders);
        validateOrderNumber(orders);
        validateOrderNotOnlyDrink(orders);
    }

    public static Orders from(List<String> orders) {
        List<MenuAndCount> menuAndCounts = orders.stream()
                .map(MenuAndCount::from)
                .toList();
        validateOrders(menuAndCounts);
        return new Orders(menuAndCounts);
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

    private static void validateOrderNotOnlyDrink(List<MenuAndCount> orders) {
        orders.forEach(menuAndCount -> System.out.println(menuAndCount.getMenuName()
                + " - " + menuAndCount.isCategory(Category.BEVERAGE)));
        if (isAllDrink(orders)) {
            throw PromotionExceptionMaker.ALL_ORDER_DRINK.makeException();
        }
    }

    private static boolean isAllDrink(List<MenuAndCount> orders) {
        return orders.stream()
                .allMatch(menuAndCount -> menuAndCount.isCategory(Category.BEVERAGE));
    }

    private static void validateDuplicateMenu(List<MenuAndCount> orders) {
        int distinctMenuCount = (int) orders.stream()
                .map(MenuAndCount::getMenuName)
                .distinct()
                .count();
        if (distinctMenuCount != orders.size()) {
            throw PromotionExceptionMaker.DUPLICATE_ORDER.makeException();
        }
    }

    private static void validateOrderNumber(List<MenuAndCount> orders) {
        int orderNumber = orders.stream()
                .mapToInt(MenuAndCount::getCount)
                .sum();
        if (orderNumber > 20) {
            throw PromotionExceptionMaker.TOO_MANY_ORDERS.makeException();
        }
    }
}
