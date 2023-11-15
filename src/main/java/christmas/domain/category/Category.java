package christmas.domain.category;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum Category {

    APPETIZER("애피타이저"),
        MUSHROOM_SOUP("양송이수프", 6_000),
        TAPAS("타바스", 5_500),
        CAESAR_SALAD("시저샐러드", 8_000),

    MAIN("메인"),
        T_BONE_STEAK("티본스테이크", 55_000),
        BBQ_RIBS("바비큐립", 54_000),
        SEAFOOD_PASTA("해산물파스타", 35_000),
        CHRISTMAS_PASTA("크리스마스파스타", 25_000),

    DESSERT("디저트"),
        CHOCOLATE_CAKE("초코케이크", 15_000),
        ICE_CREAM("아이스크림", 5_000),

    BEVERAGE("음료"),
        ZERO_COLA("제로콜라", 3_000),
        RED_WINE("레드와인", 60_000),
        CHAMPAGNE("샴페인", 25_000);

    private final String menu;
    private final int price;

    Category(final String menu) {
        this.menu = menu;
        this.price = 0;
    }

    Category(final String menu, final int price) {
        this.menu = menu;
        this.price = price;
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
}
