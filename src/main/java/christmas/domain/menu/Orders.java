package christmas.domain.menu;

import christmas.exception.PromotionExceptionMaker;
import java.util.List;

public class Orders {
    public static final String ORDER_DELIMITER = " - ";
    public static final int MAX_ORDER_NUM = 20;
    private final List<MenuAndCount> orders;

    public Orders(List<MenuAndCount> orders) {
        this.orders = orders;
    }

    public static Orders from(List<String> orders) {
        List<MenuAndCount> menuAndCounts = orders.stream()
                .map(MenuAndCount::from)
                .toList();
        validateOrders(menuAndCounts);
        return new Orders(menuAndCounts);
    }

    private static void validateOrders(List<MenuAndCount> orders) {
        validateDuplicateMenu(orders);
        validateOrderNumber(orders);
        validateOrderNotOnlyDrink(orders);
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
        if (orderNumber > MAX_ORDER_NUM) {
            throw PromotionExceptionMaker.TOO_MANY_ORDERS.makeException();
        }
    }

    private static void validateOrderNotOnlyDrink(List<MenuAndCount> orders) {
        orders.forEach(menuAndCount -> System.out.println(menuAndCount.getMenuName()
                + ORDER_DELIMITER + menuAndCount.isCategory(Category.BEVERAGE)));
        if (isAllDrink(orders)) {
            throw PromotionExceptionMaker.ALL_ORDER_DRINK.makeException();
        }
    }

    public int calcTotalPrice() {
        return orders.stream()
                .mapToInt(MenuAndCount::calcPrice)
                .sum();
    }

    public boolean hasCategoryOf(Category category) {
        return orders.stream()
                .anyMatch(menuAndCount -> menuAndCount.isCategory(category));
    }

    public int calcMenuCountOf(Category category) {
        return orders.stream()
                .filter(menuAndCount -> menuAndCount.isCategory(category))
                .mapToInt(MenuAndCount::getCount)
                .sum();
    }

    public List<MenuAndCount> getOrders() {
        return orders;
    }
}
