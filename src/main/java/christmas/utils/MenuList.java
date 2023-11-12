package christmas.utils;

import christmas.enums.menu.AppetizerMenu;
import christmas.enums.menu.BeverageMenu;
import christmas.enums.menu.DessertMenu;
import christmas.enums.menu.MainMenu;
import christmas.enums.menu.MenuItem;
import christmas.enums.menu.None;
import christmas.exceptions.IllegalOrderFormatException;
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

        for (None none : None.values()) {
            menuItems.put(none.getName(), none);
        }
    }

    private MenuList() {
    }

    public static MenuItem getMenuByName(String name) {
        MenuItem menuItem = menuItems.get(name);
        if(!menuItems.containsKey(name)){
            throw new IllegalOrderFormatException();
        }
        return menuItem;
    }
}
