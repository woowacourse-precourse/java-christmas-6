package christmas.promotion.vo;

public record Quantity(Integer quantity) {

    public Quantity add(final Quantity quantity) {
        return new Quantity(this.quantity + quantity.quantity());
    }
}
