package christmas.model;

public enum FoodType {
    양송이수프("Appetizer"), 타파스("Appetizer"), 시저샐러드("Appetizer"),
    티본스테이크("Main"), 바비큐립("Main"), 해산물파스타("Main"), 크라스마스파스타("Main"),
    초코케이크("dessert"), 아이스크림("dessert"),
    제로콜라("drink"), 레드와인("drink"), 샴페인("drink");

    private final String type;

    FoodType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
