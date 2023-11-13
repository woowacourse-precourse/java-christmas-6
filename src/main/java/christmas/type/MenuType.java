package christmas.type;

public enum MenuType {
    SOUP("애피타이저", "양송이수프", 6000),
    TAPAS("애피타이저", "타파스", 5000),
    SALAD("애피타이저", "시저샐러드", 8000),
    STEAK("메인", "티본스테이크", 55000),
    SEA_FOOD_PASTA("메인", "해산물파스타", 35000),
    CHRIST_MAS_PASTA("메인", "크리스마스파스타", 25000),
    CAKE("디저트", "초코케이크", 15000),
    ICE_CREAM("디저트", "아이스크림", 5000),
    COKE("음료", "제로콜라", 3000),
    WINE("음료", "레드와인", 60000),
    CHAMPAGNE("음료", "샴페인", 25000);

    private String type;
    private String foodName;
    private int price;

    MenuType(String type, String foodName, int price) {
        this.type = type;
        this.foodName = foodName;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getPrice() {
        return price;
    }
}
