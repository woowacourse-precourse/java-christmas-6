package christmas.enums;

public enum AppetizerMenu {
    BUTTON_MUSHROOM_SOUP(6_000)
    , TAPAS(5_500)
    , CAESAR_SALAD(8_000);

    private final Integer price;

    AppetizerMenu(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
