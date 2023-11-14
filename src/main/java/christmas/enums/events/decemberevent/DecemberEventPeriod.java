package christmas.enums.events.decemberevent;

public enum DecemberEventPeriod {
    YEAR(2023), MONTH(12), START_OF_THE_MONTH(1), END_OF_THE_MONTH(31), END_OF_THE_CHRISTMAS(25);
    private final Integer value;

    DecemberEventPeriod(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
