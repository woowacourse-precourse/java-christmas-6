package christmas.domain;

public class Discount implements Benefit {
    private final int discount;

    public Discount(int discount) {
        this.discount = discount;

    }

    @Override
    public int calculate() {
        return discount;
    }
}
