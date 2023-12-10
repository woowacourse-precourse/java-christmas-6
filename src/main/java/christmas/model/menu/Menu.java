package christmas.model.menu;

import static christmas.common.ExceptionMessage.ERROR_INVALID_MENU_SELECTION;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP(MenuCategory.APPETIZER, "양송이수프", 6000),
    TAPAS(MenuCategory.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(MenuCategory.APPETIZER, "시저샐러드", 8000),
    T_BONE_STEAK(MenuCategory.MAIN, "티본스테이크", 55000),
    BBQ_RIBS(MenuCategory.MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(MenuCategory.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(MenuCategory.MAIN, "크리스마스파스타", 25000),
    CHOCOLATE_CAKE(MenuCategory.DESSERT, "초코케이크", 15000),
    ICE_CREAM(MenuCategory.DESSERT, "아이스크림", 5000),
    ZERO_COKE(MenuCategory.BEVERAGE, "제로콜라", 3000),
    RED_WINE(MenuCategory.BEVERAGE, "레드와인", 60000),
    CHAMPAGNE(MenuCategory.BEVERAGE, "샴페인", 25000);

    private final MenuCategory menuCategory;
    private final String korean;
    private final double price;

    Menu(MenuCategory menuCategory, String korean, double price) {
        this.menuCategory = menuCategory;
        this.korean = korean;
        this.price = price;
    }

    public static Menu nameOf(String korean) {
        return Arrays.stream(values())
                .filter(menu -> menu.korean.equals(korean))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_INVALID_MENU_SELECTION));
    }

    public String getKorean() {
        return korean;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public double getPrice() {
        return price;
    }
}
