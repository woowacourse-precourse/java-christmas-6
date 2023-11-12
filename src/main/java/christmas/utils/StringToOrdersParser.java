package christmas.utils;

import christmas.enums.menu.BeverageMenu;
import christmas.exceptions.IllegalOnlyBeverageException;
import christmas.exceptions.IllegalOrderFormatException;
import christmas.exceptions.IllegalOutOfNumberMenuException;
import christmas.order.Order;
import christmas.order.Orders;
import java.util.HashSet;
import java.util.Set;

public class StringToOrdersParser {

    public static Orders parseInputToOrderSet(String input) {
        Set<Order> menuSet = new HashSet<>();
        String[] inputs = input.split(",");
        for (String menu : inputs) {
            parseSingleOrderFromInput(menuSet, menu);
        }
        isNotOnlyBeverage(menuSet);
        isQuantityNotOver20(menuSet);
        return new Orders(menuSet);
    }

    private static void isQuantityNotOver20(Set<Order> menuSet) {
        if (menuSet.stream().mapToInt(Order::getOrderQuantity).sum() > 20) {
            throw new IllegalOutOfNumberMenuException();
        }
    }

    private static void isNotOnlyBeverage(Set<Order> menuSet) {
        if (menuSet.stream().allMatch(order -> order.getMenuItem() instanceof BeverageMenu)) {
            throw new IllegalOnlyBeverageException();
        }
    }

    private static void parseSingleOrderFromInput(Set<Order> menuList, String menu) {
        try {
            createOrderFromMenuItem(menuList, menu);
        } catch (NumberFormatException e) {
            throw new IllegalOrderFormatException();
        }
    }

    private static void createOrderFromMenuItem(Set<Order> menuSet, String menu) {
        String[] menuAndQuantity = menu.split("-");
        isCorrectForm(menuAndQuantity);
        String menuItem = menuAndQuantity[0].trim();
        int quantity = Integer.parseInt(menuAndQuantity[1].trim());
        isCorrectQuantity(quantity);
        Order order = new Order(MenuList.getMenuByName(menuItem), quantity);
        isExistMenu(menuSet, order);
    }

    private static void isCorrectForm(String[] menuAndQuantity) {
        if (menuAndQuantity.length != 2) {
            throw new IllegalOrderFormatException();
        }
    }

    private static void isCorrectQuantity(int quantity) {
        if (quantity == 0) {
            throw new IllegalOrderFormatException();
        }
    }

    private static void isExistMenu(Set<Order> menuSet, Order order) {
        if (!menuSet.add(order)) {
            throw new IllegalOrderFormatException();
        }
    }


}
