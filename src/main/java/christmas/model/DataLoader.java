package christmas.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private final String law_menu_data = """
            <애피타이저>
            양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)
                        
            <메인>
            티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)
                        
            <디저트>
            초코케이크(15,000), 아이스크림(5,000)
                        
            <음료>
            제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
            """;

    private final int categoryIdx = 0;
    private final int menuOffsetIdx = 1;

    private final List<Menu> menu;

    public DataLoader () {
        menu = new ArrayList<>();
    }

    public void loadMenuData () {
        List<String> menusByCategory = Arrays.stream(law_menu_data.split("\n\n")).toList();

        for (int menuIdx = 0; menuIdx < menusByCategory.size(); menuIdx++) {
            divideMenuByCategory(menuIdx, menusByCategory.get(menuIdx));
        }
    }

    //    category별 menu
    private void divideMenuByCategory (int menu, String menusByCategory) {
        String[] categoryAndMenu = menusByCategory.split("\n");

        String categoryName = categoryAndMenu[categoryIdx];
        categoryName = categoryName.substring(1, categoryName.length() - 1);

        String[] menus = categoryAndMenu[menuOffsetIdx].split(", ");
        Category category = new Category(menu, categoryName);

        for (int i = 0; i < menus.length; i++ ){
            saveMenu(category, menus[i]);
        }
    } // TODO : split해서 넘기기만 하는데 굳이 Collection을 사용할 이유가 있을까?에 대한 고민

    private void saveMenu(Category category, String menus) {
        String menuName = menus.substring(0, menus.indexOf("("));
        String priceStr = menus.substring(menus.indexOf("(") + 1, menus.indexOf(")"));

        int price =  Integer.parseInt(String.join("", priceStr.split(",")));

        Menu menuToSave = new Menu(category, menuName, price);
        this.menu.add(menuToSave);
    }
    // TODO : 변수명 어떡하죠?

}