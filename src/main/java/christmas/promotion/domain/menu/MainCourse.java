package christmas.promotion.domain.menu;

public enum MainCourse implements Menu {
    T_BONE_STEAK("티본스테이크", 55000.0),
    BARBECUE_RIBS("바비큐립", 54000.0),
    SEAFOOD_PASTA("해산물파스타", 35000.0),
    CHRISTMAS_PASTA("크리스마스파스타", 25000.0);

    private final String name;
    private final double price;

    MainCourse(final String name, final double price) {
        this.name = name;
        this.price = price;
    }

    public String description() {
        return "<메인>";
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
