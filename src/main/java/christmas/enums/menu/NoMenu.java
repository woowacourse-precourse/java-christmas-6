package christmas.enums.menu;

public enum NoMenu implements MenuItem{
    NO_MENU("없음",0);

    private final String name;
    private final Integer amount;

    NoMenu(String name, Integer amount) {
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
