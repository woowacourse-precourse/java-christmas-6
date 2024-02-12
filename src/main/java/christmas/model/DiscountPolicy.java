package christmas.model;

public enum DiscountPolicy {
    CHRISTMAS_DISCOUNT_START(1000),
    CHRISTMAS_DISCOUNT_INCREMENT(100),
    WEEKDAY_DISCOUNT(2023),
    WEEKEND_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    GIFT_DISCOUNT(25000);

    private final int value;

    DiscountPolicy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
