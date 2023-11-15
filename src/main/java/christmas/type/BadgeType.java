package christmas.type;

public enum BadgeType {
    SANTA("산타", 20000),
    TREE("트티", 10000),
    STAR("별", 5000);

    private String badgeName;
    private int price;

    BadgeType(String badgeName, int price) {
        this.badgeName = badgeName;
        this.price = price;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getPrice() {
        return price;
    }
}
