package christmas.type;

public enum FoodType {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");


    private String type;

    FoodType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
