package christmas.domain.entity.menu;

import christmas.domain.constants.MenuCategoryEnum;

public class Menu {
    private String name;
    private MenuCategoryEnum.MenuCategory menuCategory;
    private int price;

    public MenuCategoryEnum.MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Menu(String name, MenuCategoryEnum.MenuCategory menuCategory, int price) {
        this.name = name;
        this.menuCategory = menuCategory;
        this.price = price;
    }
}
