package christmas.enums;

import java.util.HashMap;
import java.util.Map;

public enum BeverageMenu implements MenuItem{
    ZERO_COKE("제로콜라",3_000)
    , RED_WINE("레드와인",60_000)
    , CHAMPAGNE("샴페인",25_000);
    private final Integer price;
    private final String name;

    BeverageMenu(String name,Integer price) {
        this.name = name;
        this.price = price;
    }

    static {
        for (BeverageMenu value : BeverageMenu.values()) {
            NAME_TO_ITEM_MAP.put(value.getName(),value);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
