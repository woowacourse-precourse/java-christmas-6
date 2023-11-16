package christmas.promotion.domain.menu;

public enum Dessert implements Menu {
    CHOCOLATE_CAKE("초코케이크", 15000.0),
    ICE_CREAM("아이스크림", 5000.0);

    private final String name;
    private final double price;

    Dessert(final String name, final double price) {
        this.name = name;
        this.price = price;
    }

    public String description() {
        return "<디저트>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}