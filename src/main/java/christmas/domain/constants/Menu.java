package christmas.domain.constants;

public enum Menu {
    YANGSONGI_SOUP(6000, "양송이수프", MenuCategory.APPETIZER),
    TAPAS(5500, "타파스", MenuCategory.APPETIZER),
    CAESAR_SALAD(8000, "시저샐러드", MenuCategory.APPETIZER),

    T_BONE_STEAK(55000, "티본스테이크", MenuCategory.MAIN),
    BARBECUE_RIBS(54000, "바비큐립", MenuCategory.MAIN),
    SEAFOOD_PASTA(35000, "해산물파스타", MenuCategory.MAIN),
    CHRISTMAS_PASTA(25000, "크리스마스파스타", MenuCategory.MAIN),

    CHOCOLATE_CAKE(15000, "초코케이크", MenuCategory.DESSERT),
    ICECREAM(5000, "아이스크림", MenuCategory.DESSERT),
    
    ZERO_COLA(3000, "제로콜라", MenuCategory.DRINK),
    RED_WINE(60000, "레드와인", MenuCategory.DRINK),
    CHAMPAGNE(25000, "샴페인", MenuCategory.DRINK);

    private int price;
    private String name;
    private MenuCategory category;

    Menu(int price, String name, MenuCategory category) {
        this.price = price;
        this.name = name;
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public MenuCategory getCategory() {
        return category;
    }
}
