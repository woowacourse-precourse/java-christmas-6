package christmas.enums;

import java.util.HashMap;
import java.util.Map;

public class MenuList {
    private final static Map<String, MenuItem> menuItems = new HashMap<>();

    static {
        for (AppetizerMenu appetizerMenu : AppetizerMenu.values()) {
            menuItems.put(appetizerMenu.getName(), appetizerMenu);
        }

        for (BeverageMenu beverageMenu : BeverageMenu.values()) {
            menuItems.put(beverageMenu.getName(), beverageMenu);
        }

        for (DessertMenu dessertMenu : DessertMenu.values()) {
            menuItems.put(dessertMenu.getName(), dessertMenu);
        }

        for (MainMenu mainMenu : MainMenu.values()) {
            menuItems.put(mainMenu.getName(), mainMenu);
        }
    }

    private MenuList() {
    }

    public static MenuItem getMenuByName(String name) {
        return menuItems.get(name);
    }
}
