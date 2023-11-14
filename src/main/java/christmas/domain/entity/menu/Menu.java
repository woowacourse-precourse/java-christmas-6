package christmas.domain.entity.menu;

import christmas.domain.constants.Enum;

public class Menu {
    private String name;
    private Enum.MenuCategory menuCategory;
    private int price;

    public Enum.MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public int getPrice() {
        return price;
    }

    public Menu(String name, Enum.MenuCategory menuCategory, int price) {
        this.name = name;
        this.menuCategory = menuCategory;
        this.price = price;
    }
}
