package christmas.enrtity.menu;

import christmas.constants.Enum;

public class Menu {
    private String name;
    private Enum.MenuCategory menuCategory;
    private int price;

    public Menu(String name, Enum.MenuCategory menuCategory, int price) {
        this.name = name;
        this.menuCategory = menuCategory;
        this.price = price;
    }
}
