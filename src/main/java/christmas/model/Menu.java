package christmas.model;

import static christmas.model.MenuType.*;

public enum Menu {

    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BARBECUE_RIB("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, DESERT),
    ICE_CREAM("아이스크림", 5000, DESERT),
    ZERO_COKE("제로콜라", 3000, DRINK),
    RED_WINE("레드와인", 60000, DRINK),
    CHAMPAGNE("샴페인", 25000, DRINK);

    private String name;
    private Integer price;
    private MenuType type;

    Menu(String name, Integer price, MenuType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu getEnumByName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public MenuType getType() {
        return type;
    }
}
