package christmas.domain.constants;

public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    DRINK("음료");

    private String category;

    MenuCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
