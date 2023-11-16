package christmas.promotion.domain.menu;

public enum Appetizer implements Menu {
    MUSHROOM_SOUP("양송이수프", 6000.0),
    TAPAS("타파스", 5500.0),
    CAESAR_SALAD("시저샐러드", 8000.0);

    private final String name;
    private final double price;

    Appetizer(final String name, final double price) {
        this.name = name;
        this.price = price;
    }

    public String description() {
        return "<애피타이저>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
