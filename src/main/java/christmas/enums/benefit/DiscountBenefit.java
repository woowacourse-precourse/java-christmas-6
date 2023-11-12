package christmas.enums.benefit;

public enum DiscountBenefit {
    BASIC_BENEFIT(1_000)
    , INCREASE_BENEFIT(100)
    , WEEK_BENEFIT(2_023)
    , GIFT_CONDITION_BENEFIT(120_000)
    , NO_BENEFIT(0)
    , MINIMUM_REQUIRE_AMOUNT(10_000);
    
    private final Integer amount;

    DiscountBenefit(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }
}
