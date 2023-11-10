package christmas.enums.menu;

public enum BeverageMenu implements MenuItem {
    ZERO_COKE("제로콜라", 3_000), RED_WINE("레드와인", 60_000), CHAMPAGNE("샴페인", 25_000);
    private final Integer price;
    private final String name;

    BeverageMenu(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
