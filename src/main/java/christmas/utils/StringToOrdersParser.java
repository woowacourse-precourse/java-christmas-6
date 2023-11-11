package christmas.utils;

import christmas.exceptions.IllegalOrderFormatException;
import christmas.order.Order;
import java.util.HashSet;
import java.util.Set;

public class StringToOrdersParser {

    public static Set<Order> parseInputToOrderSet(String input) {
        Set<Order> menuSet = new HashSet<>();
        String[] inputs = input.split(",");
        for (String menu : inputs) {
            parseSingleOrderFromInput(menuSet, menu);
        }
        return menuSet;
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
        if (menuAndQuantity.length != 2) {
            throw new IllegalOrderFormatException();
        }
        String menuItem = menuAndQuantity[0].trim();
        String quantity = menuAndQuantity[1].trim();
        Order order = new Order(MenuList.getMenuByName(menuItem),
                Integer.parseInt(quantity));
        if (!menuSet.add(order)) {
            throw new IllegalOrderFormatException();
        }
    }


}
