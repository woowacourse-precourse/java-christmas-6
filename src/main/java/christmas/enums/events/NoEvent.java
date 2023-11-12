package christmas.enums.events;

public enum NoEvent implements Events{
    NO_EVENT("없음",0);
    private final String name;
    private final Integer amount;

    NoEvent(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
