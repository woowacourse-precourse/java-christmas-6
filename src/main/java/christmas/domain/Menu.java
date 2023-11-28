package christmas.domain;

public enum Menu {
    SOFT_MUSHROOM_SOUP("appetizer", 6_000, "양송이수프"),
    TAPAS("appetizer", 5_500, "타파스"),
    CAESAR_SALAD("appetizer", 8_000, "시저샐러드"),
    T_BONE_STEAK("main", 55_000, "티본스테이크"),
    BARBEQUE_RIP("main", 54_000, "바비큐립"),
    SEAFOOD_PASTA("main", 35_000, "해산물파스타"),
    CHRISTMAS_PASTA("main", 25_000, "크리스마스파스타"),
    CHOCO_CAKE("dessert", 15_000, "초코케이크"),
    ICE_CREAM("dessert", 5_000, "아이스크림"),
    ZERO_COKE("drink", 3_000, "제로콜라"),
    RED_WINE("drink", 60_000, "레드와인"),
    CHAMPAGNE("drink", 25_000, "샴페인");

    private final String menuType;
    private final int price;
    private final String koreanName;

    Menu(String menuType, int price, String koreanName){
        this.menuType = menuType;
        this.price = price;
        this.koreanName = koreanName;
    }

    public boolean isFoodOnMenu(String food){
        if(food.equals(koreanName)){
            return true;
        }

        return false;
    }

    public int cost(String food){
        int cost = 0;

        if(food.equals(koreanName)){
            cost = price;
        }

        return cost;
    }
}
