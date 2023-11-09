package christmas.enums;

public enum MainMenu {
    T_BONE_STEAK(55_000)
    , BARBECUE_RIBS(54_000)
    , SEAFOOD_PASTA(35_000)
    , CHRISTMAS_PASTA(25_000);

    private final Integer price;

    MainMenu(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}
