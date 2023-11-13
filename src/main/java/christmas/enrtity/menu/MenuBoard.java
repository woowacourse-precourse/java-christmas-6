package christmas.enrtity.menu;

import christmas.constants.Enum;

import java.util.HashMap;
import java.util.Map;

public class MenuBoard {
    public Map<String, Menu> menus = new HashMap<>();
    public Map<String, Menu> initMenus(){
        menus.put("양송이수프", new Menu("양송이수프", Enum.MenuCategory.APPETIZER, 6000));
        menus.put("타파스", new Menu("타파스", Enum.MenuCategory.APPETIZER, 5000));
        menus.put("시저샐러드", new Menu("시저샐러드", Enum.MenuCategory.APPETIZER, 8000));
        menus.put("티본스테이크", new Menu("티본스테이크", Enum.MenuCategory.MAIN, 5500));
        menus.put("바비큐립", new Menu("바비큐립", Enum.MenuCategory.MAIN, 54000));
        menus.put("해산물파스타", new Menu("해산물파스타", Enum.MenuCategory.MAIN, 35000));
        menus.put("크리스마스파스타", new Menu("크리스마스파스타", Enum.MenuCategory.MAIN, 25000));
        menus.put("초코케이크", new Menu("초코케이크", Enum.MenuCategory.DESSERT, 15000));
        menus.put("아이스크림", new Menu("아이스크림", Enum.MenuCategory.DESSERT, 5000));
        menus.put("제로콜라", new Menu("제로콜라", Enum.MenuCategory.BEVERAGE, 3000));
        menus.put("레드와인", new Menu("레드와인", Enum.MenuCategory.BEVERAGE, 60000));
        menus.put("샴페인", new Menu("샴페인", Enum.MenuCategory.BEVERAGE, 25000));
        return menus;
    }
}
