package christmas.domain.category;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Category {

    ROOT("메뉴판", null),
        APPETIZER("애피타이저", ROOT),
            MUSHROOM_SOUP("양송이수프", 6_000, APPETIZER),
            TAPAS("타파스", 5_500, APPETIZER),
            CAESAR_SALAD("시저샐러드", 8_000, APPETIZER),

        MAIN("메인", ROOT),
            T_BONE_STEAK("티본스테이크", 55_000, MAIN),
            BBQ_RIBS("바비큐립", 54_000, MAIN),
            SEAFOOD_PASTA("해산물파스타", 35_000, MAIN),
            CHRISTMAS_PASTA("크리스마스파스타", 25_000, MAIN),

        DESSERT("디저트", ROOT),
            CHOCOLATE_CAKE("초코케이크", 15_000, DESSERT),
            ICE_CREAM("아이스크림", 5_000, DESSERT),

        BEVERAGE("음료", ROOT),
            ZERO_COLA("제로콜라", 3_000, BEVERAGE),
            RED_WINE("레드와인", 60_000, BEVERAGE),
            CHAMPAGNE("샴페인", 25_000, BEVERAGE);

    private final String menu;
    private final int price;

    private final Category parentCategory;

    Category(final String menu, Category parentCategory) {
        this.menu = menu;
        this.price = 0;
        this.parentCategory = parentCategory;
    }

    Category(final String menu, final int price, Category parentCategory) {
        this.menu = menu;
        this.price = price;
        this.parentCategory = parentCategory;
    }

    public static Optional<Category> of(final String menu) {
        return Arrays.stream(Category.values())
                .filter(
                        name -> name.matched(menu)
                                && name.getPrice() != 0
                )
                .findFirst();
    }

    private boolean matched(final String menu) {
        return Objects.equals(this.menu, menu);
    }

    public String getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

    public String getParentCategory() {
        return parentCategory.getMenu();
    }
}
