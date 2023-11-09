package christmas.enums;

import java.util.HashMap;
import java.util.Map;

public enum AppetizerMenu implements MenuItem {
    BUTTON_MUSHROOM_SOUP("양송이수프",6_000)
    , TAPAS("타파스",5_500)
    , CAESAR_SALAD("시저샐러드",8_000);
    private final Integer price;
    private final String name;

    AppetizerMenu(String name,Integer price) {
        this.price = price;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    static {
        for (AppetizerMenu value : AppetizerMenu.values()) {
            NAME_TO_ITEM_MAP.put(value.getName(),value);
        }

    }



}
