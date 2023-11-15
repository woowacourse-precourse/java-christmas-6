package christmas.domain.constants;

public class MenuEnum {
    public enum Menus{
        MUSHROOM_SOUP("양송이수프", MenuCategoryEnum.MenuCategory.APPETIZER, 6000),
        TAPAS("타파스", MenuCategoryEnum.MenuCategory.APPETIZER, 5500),
        CAESAR_SALAD("시저샐러드", MenuCategoryEnum.MenuCategory.APPETIZER, 8000),
        TBONE_STEAK("티본스테이크", MenuCategoryEnum.MenuCategory.MAIN, 55000),
        BARBCUE_RIBS("바비큐립", MenuCategoryEnum.MenuCategory.MAIN, 54000),
        SEA_PASTA("해산물파스타", MenuCategoryEnum.MenuCategory.MAIN, 35000),
        CHRISTMAS_PASTA("크리스마스파스타", MenuCategoryEnum.MenuCategory.MAIN, 25000),
        CHOCOCAKE("초코케이크", MenuCategoryEnum.MenuCategory.DESSERT, 15000),
        ICECREAM("아이스크림", MenuCategoryEnum.MenuCategory.DESSERT, 5000),
        ZERO_COKE("제로콜라", MenuCategoryEnum.MenuCategory.BEVERAGE, 3000),
        REDWINE("레드와인", MenuCategoryEnum.MenuCategory.BEVERAGE, 60000),
        CHAMPAGNE("샴페인", MenuCategoryEnum.MenuCategory.BEVERAGE, 25000);

        private final String name;
        private final MenuCategoryEnum.MenuCategory category;
        private final int price;

        Menus(String name, MenuCategoryEnum.MenuCategory category, int price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public MenuCategoryEnum.MenuCategory getCategory() {
            return category;
        }

        public int getPrice() {
            return price;
        }
    }

}
