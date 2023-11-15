package christmas.model;

import java.util.*;

public class RestaurantDatabase {
    private static final String LAW_MENU_DATA = """
            <애피타이저>
            양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
                        
            <메인>
            티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
                        
            <디저트>
            초코케이크(15,000), 아이스크림(5,000)
                        
            <음료>
            제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
            """;

    private static final int CATEGORY_IDX = 0;
    private static final int MENU_OFFSET_IDX = 1;

    private final List<Menu> menu;

    public RestaurantDatabase() {
        menu = new ArrayList<>();
    }

    public List<Menu> loadMenuData () {
        List<String> menusByCategory = Arrays.stream(LAW_MENU_DATA.split("\n\n")).toList();

        for (int menuIdx = 0; menuIdx < menusByCategory.size(); menuIdx++) {
            divideMenuByCategory(menuIdx, menusByCategory.get(menuIdx));
        }

        return Collections.unmodifiableList(menu);
    }

    //    category별 menu
    private void divideMenuByCategory (int menu, String menusByCategory) {
        String[] categoryAndMenu = menusByCategory.split("\n");

        String categoryName = categoryAndMenu[CATEGORY_IDX];
        categoryName = categoryName.substring(1, categoryName.length() - 1);

        String[] menus = categoryAndMenu[MENU_OFFSET_IDX].split(", ");
        Category category = new Category(menu, categoryName);

        for (String menuInfoToStore : menus) {
            saveMenu(category, menuInfoToStore);
        }
    }

    private void saveMenu(Category category, String menus) {
        String menuName = menus.substring(0, menus.indexOf("("));
        String priceStr = menus.substring(menus.indexOf("(") + 1, menus.indexOf(")"));

        int price =  Integer.parseInt(String.join("", priceStr.split(",")));

        Menu menuToSave = new Menu(category, menuName, price);
        this.menu.add(menuToSave);
    }
}