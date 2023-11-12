package christmas.model;

public enum GiftPrice {
    샴페인(25000), 없음(0);
    private final int price;

    GiftPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
