package christmas.enums;

public enum BeverageMenu {
    ZERO_COKE(3_000)
    , RED_WINE(60_000)
    , CHAMPAGNE(25_000);
    private final Integer price;

    BeverageMenu(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
