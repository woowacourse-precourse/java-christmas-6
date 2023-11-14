package christmas.domain.entity.menu;

import christmas.domain.constants.Enum;

import java.util.HashMap;
import java.util.Map;

public class MenuBoard {
    private static Map<String, Menu> menus = initMenus();

    public static Map<String, Menu> getMenus() {
        return menus;
    }

    private static Map<String, Menu> initMenus(){
        Map<String, Menu> menuMap = new HashMap<>();
        menuMap.put("양송이수프", new Menu("양송이수프", Enum.MenuCategory.APPETIZER, 6000));
        menuMap.put("타파스", new Menu("타파스", Enum.MenuCategory.APPETIZER, 5000));
        menuMap.put("시저샐러드", new Menu("시저샐러드", Enum.MenuCategory.APPETIZER, 8000));
        menuMap.put("티본스테이크", new Menu("티본스테이크", Enum.MenuCategory.MAIN, 5500));
        menuMap.put("바비큐립", new Menu("바비큐립", Enum.MenuCategory.MAIN, 54000));
        menuMap.put("해산물파스타", new Menu("해산물파스타", Enum.MenuCategory.MAIN, 35000));
        menuMap.put("크리스마스파스타", new Menu("크리스마스파스타", Enum.MenuCategory.MAIN, 25000));
        menuMap.put("초코케이크", new Menu("초코케이크", Enum.MenuCategory.DESSERT, 15000));
        menuMap.put("아이스크림", new Menu("아이스크림", Enum.MenuCategory.DESSERT, 5000));
        menuMap.put("제로콜라", new Menu("제로콜라", Enum.MenuCategory.BEVERAGE, 3000));
        menuMap.put("레드와인", new Menu("레드와인", Enum.MenuCategory.BEVERAGE, 60000));
        menuMap.put("샴페인", new Menu("샴페인", Enum.MenuCategory.BEVERAGE, 25000));
        return menuMap;
    }
}
