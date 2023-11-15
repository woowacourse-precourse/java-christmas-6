package christmas.domain.constants;

public class DiscountPolicyEnum {
    public enum Discount{
        D_DAY_START_DISCOUNTING_AMOUNT(900),
        D_DAY_DAILY_DISCOUNTING_AMOUNT(100),
        WEEKLY_DISCOUNTING_AMOUNT(2023),
        SPECIAL_DISCOUNTING_AMOUNT(1000),
        APPLIED_DISCOUNT_AMOUNT_GET_SANTA(20000),
        APPLIED_DISCOUNT_AMOUNT_GET_TREE(10000),
        APPLIED_DISCOUNT_AMOUNT_GET_STAR(5000),
        APPLIED_AMOUNT_GET_GIFT(120000),
        MINIMUM_AMOUNT_TO_DISCOUNT(10000);

        private final int value;

        Discount(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum Badge{
        SANTA_BADGE("산타"),
        TREE_BADGE("트리"),
        STAR_BADGE("별");

        private final String name;

        Badge(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
