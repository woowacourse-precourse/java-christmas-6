package christmas.domain.entity.menu;

import christmas.domain.constants.MenuCategoryEnum;
import christmas.domain.constants.MenuEnum;

import java.util.HashMap;
import java.util.Map;

public class MenuBoard {
    private static final Map<String, Menu> menus = initMenus();

    public static Map<String, Menu> getMenus() {
        return menus;
    }

    private static Map<String, Menu> initMenus(){
        Map<String, Menu> menuMap = new HashMap<>();
        menuMapPut(menuMap, MenuEnum.Menus.MUSHROOM_SOUP);
        menuMapPut(menuMap, MenuEnum.Menus.TAPAS);
        menuMapPut(menuMap, MenuEnum.Menus.CAESAR_SALAD);
        menuMapPut(menuMap, MenuEnum.Menus.TBONE_STEAK);
        menuMapPut(menuMap, MenuEnum.Menus.BARBCUE_RIBS);
        menuMapPut(menuMap, MenuEnum.Menus.SEA_PASTA);
        menuMapPut(menuMap, MenuEnum.Menus.CHRISTMAS_PASTA);
        menuMapPut(menuMap, MenuEnum.Menus.CHOCOCAKE);
        menuMapPut(menuMap, MenuEnum.Menus.ICECREAM);
        menuMapPut(menuMap, MenuEnum.Menus.ZERO_COKE);
        menuMapPut(menuMap, MenuEnum.Menus.REDWINE);
        menuMapPut(menuMap, MenuEnum.Menus.CHAMPAGNE);
        return menuMap;
    }

    private static void menuMapPut(Map<String, Menu> menuMap, MenuEnum.Menus menu){
        menuMap.put(menu.getName(), new Menu(menu.getName(), menu.getCategory(), menu.getPrice()));
    }
}
