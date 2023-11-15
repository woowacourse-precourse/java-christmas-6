package christmas.domain.constants;

public class DateEnum {
    public enum Date{
        PRESENT_YEAR(2023),
        DECEMBER(12),
        DATE_OF_CHRISTMAS(25),
        LAST_DAY_OF_MONTH(31);

        private final int value;

        Date(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
