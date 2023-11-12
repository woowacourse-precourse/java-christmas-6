package christmas.enums.menu;

public enum None implements MenuItem{
    NONE("NONE",0);

    private final String name;
    private final Integer amount;

    None(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getAmount() {
        return amount;
    }
}
