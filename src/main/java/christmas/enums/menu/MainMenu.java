package christmas.enums.menu;

public enum MainMenu implements MenuItem {
    T_BONE_STEAK("티본스테이크", 55_000), BARBECUE_RIBS("바베큐립", 54_000), SEAFOOD_PASTA("해산물파스타", 35_000), CHRISTMAS_PASTA(
            "크리스마스파스타", 25_000);

    private final String name;
    private final Integer price;

    MainMenu(String name, Integer price) {
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
