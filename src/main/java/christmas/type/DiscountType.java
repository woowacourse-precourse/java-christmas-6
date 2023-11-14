package christmas.type;

public enum DiscountType {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인"),
    NORMAL_DISCOUNT("평일 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIVING_DISCOUNT("증정 이벤트");

    private String discountText;

    DiscountType(String discountText) {
        this.discountText = discountText;
    }

    public String getDiscountText() {
        return discountText;
    }
}
