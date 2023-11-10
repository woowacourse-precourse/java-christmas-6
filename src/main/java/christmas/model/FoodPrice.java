package christmas.model;

public enum FoodPrice {
    양송이수프(6000), 타파스(5500), 시저샐러드(8000),
    티본스테이크(55000), 바비큐립(54000), 해산물파스타(35000), 크라스마스파스타(25000),
    초코케이크(15000), 아이스크림(5000),
    제로콜라(3000), 레드와인(60000), 샴페인(25000);

    private final int price;

    FoodPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
