package christmas.domain.entity.menu;

import christmas.domain.constants.Enum;

public class Menu {
    public String name;
    public Enum.MenuCategory menuCategory;
    public int price;

    public Menu(String name, Enum.MenuCategory menuCategory, int price) {
        this.name = name;
        this.menuCategory = menuCategory;
        this.price = price;
    }
}
