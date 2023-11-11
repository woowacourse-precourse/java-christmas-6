package christmas.model;

public enum Badge {
    산타(20000), 트리(10000), 스타(5000);

    private final int price;

    Badge(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
