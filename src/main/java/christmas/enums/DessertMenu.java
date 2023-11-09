package christmas.enums;

public enum DessertMenu {
    CHOCOLATE_CAKE(15_000)
    , ICE_CREAM(5_000);
    private final Integer price;

    DessertMenu(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
