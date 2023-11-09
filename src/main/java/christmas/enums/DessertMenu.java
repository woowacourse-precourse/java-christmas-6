package christmas.enums;

public enum DessertMenu implements MenuItem{
    CHOCOLATE_CAKE("초코케이크", 15_000)
    , ICE_CREAM("아이스크림", 5_000);
    private final String name;
    private final Integer price;

    DessertMenu(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    static {
        for (DessertMenu value : DessertMenu.values()) {
            NAME_TO_ITEM_MAP.put(value.getName(),value);
        }

    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
