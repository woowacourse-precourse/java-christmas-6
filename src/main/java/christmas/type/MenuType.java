package christmas.type;

public enum MenuType {
    SOUP(FoodType.APPETIZER, "양송이수프", 6000),
    TAPAS(FoodType.APPETIZER, "타파스", 5500),
    SALAD(FoodType.APPETIZER, "시저샐러드", 8000),
    STEAK(FoodType.MAIN, "티본스테이크", 55000),
    BBQ(FoodType.MAIN, "바비큐립", 54000),
    SEA_FOOD_PASTA(FoodType.MAIN, "해산물파스타", 35000),
    CHRIST_MAS_PASTA(FoodType.MAIN, "크리스마스파스타", 25000),
    CAKE(FoodType.DESSERT, "초코케이크", 15000),
    ICE_CREAM(FoodType.DESSERT, "아이스크림", 5000),
    COKE(FoodType.DRINK, "제로콜라", 3000),
    WINE(FoodType.DRINK, "레드와인", 60000),
    CHAMPAGNE(FoodType.DRINK, "샴페인", 25000);

    private FoodType type;
    private String foodName;
    private int price;

    MenuType(FoodType type, String foodName, int price) {
        this.type = type;
        this.foodName = foodName;
        this.price = price;
    }

    public FoodType getType() {
        return type;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getPrice() {
        return price;
    }

    public static int getPriceByName(String foodName) {
        for (MenuType menu : MenuType.values()) {
            if (menu.getFoodName().equals(foodName)) {
                return menu.getPrice();
            }
        }
        throw new IllegalArgumentException("Invalid food name: " + foodName);
    }
}
