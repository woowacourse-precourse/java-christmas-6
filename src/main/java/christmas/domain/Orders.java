package christmas.domain;

import christmas.domain.constants.MenuCategory;
import christmas.global.exception.CustomException;
import christmas.global.exception.ErrorMessage;
import java.util.List;

public class Orders {
    private List<Order> orders;

    public Orders(List<Order> orders) {
        Validator.validate(orders);
        this.orders = orders;
    }

    public int getCountOfCategory(MenuCategory category) {
        return (int) orders.stream()
                .filter(order -> order.isCategory(category))
                .count();
    }

    public int calculateTotalPrice() {
        return orders.stream()
                .mapToInt(order -> order.calculatePrice())
                .sum();
    }

    private static class Validator {
        private static void validate(List<Order> orders) {
            validateDuplicatedItem(orders);
            validateAllDrinkMenu(orders);
            validateMenuCount(orders);
        }

        private static int validateMenuCount(List<Order> orders) {
            return (int) orders.stream()
                    .mapToInt(order -> order.getCount())
                    .count();
        }

        private static void validateAllDrinkMenu(List<Order> orders) {
            if (isAllDrinkMenu(orders)) {
                throw CustomException.from(ErrorMessage.INVALID_ORDER_ERROR);
            }
        }

        private static boolean isAllDrinkMenu(List<Order> orders) {
            return orders.stream()
                    .allMatch(order -> order.isCategory(MenuCategory.DRINK));
        }

        private static void validateDuplicatedItem(List<Order> items) {
            if (hasDuplicatedItem(items)) {
                throw CustomException.from(ErrorMessage.INVALID_ORDER_ERROR);
            }
        }

        private static boolean hasDuplicatedItem(List<Order> items) {
            return items.size() != calculateUniqueItemsCount(items);
        }

        private static int calculateUniqueItemsCount(List<Order> items) {
            return (int) items.stream()
                    .map(Order::getMenu)
                    .distinct()
                    .count();
        }
    }
}
