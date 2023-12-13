package christmas.domain.menu;

import java.util.List;

public enum Category {
    APPETIZER("애피타이저",
            List.of(Menu.YANGSONGEE_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD)),
    MAIN_COURSE("메인",
            List.of(Menu.T_BONE_STEAK, Menu.BARBECUE_RIBS, Menu.SEAFOOD_PASTA, Menu.CHRISTMAS_PASTA)),
    DESSERT("디저트",
            List.of( Menu.CHOCO_CAKE, Menu.ICE_CREAM )),
    BEVERAGE("음료",
            List.of( Menu.ZERO_COLA, Menu.RED_WINE, Menu.CHAMPAGNE ));

    private final String koreanName;
    private final List<Menu> menus;

    Category(String koreanName, List<Menu> menus) {
        this.koreanName = koreanName;
        this.menus = menus;
    }

    public boolean contains(Menu menu) {
        return menus.contains(menu);
    }
}
