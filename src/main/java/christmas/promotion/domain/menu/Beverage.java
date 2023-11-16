package christmas.promotion.domain.menu;

public enum Beverage implements Menu {
    ZERO_COLA("제로콜라", 3000.0),
    RED_WINE("레드와인", 60000.0),
    CHAMPAGNE("샴페인", 25000.0);

    private final String name;
    private final double price;

    Beverage(final String name, final double price) {
        this.name = name;
        this.price = price;
    }

    public String description() {
        return "<음료>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
