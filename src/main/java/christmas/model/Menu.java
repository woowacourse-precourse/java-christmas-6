package christmas.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;

public class Menu {

    private final Category category;

    private final String menuName;

    private final int price;

    public static int checkBill (List<SimpleEntry<Menu, Integer>> orderedMenu) {
        return orderedMenu.stream()
                .mapToInt(el -> el.getKey().price * el.getValue())
                .sum();
    }

    public Menu(Category category, String menuName, int price) {
        this.category = category;
        this.menuName = menuName;
        this.price = price;
    }

    public boolean checkMenu (String menuName) {
        return menuName.equals(this.menuName);
    }


    public boolean checkCategory (String category) {
        return this.category.isSame(category);
    }

    @Override
    public String toString() {
        return menuName;
    }
}
