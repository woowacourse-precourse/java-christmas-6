package christmas.enums;


import java.util.HashMap;
import java.util.Map;

public interface MenuItem {
    Map<String,MenuItem> NAME_TO_ITEM_MAP = new HashMap<>();
    String getName();
    Integer getPrice();

     static MenuItem getByName(String name){
        return NAME_TO_ITEM_MAP.get(name);
    }

}
