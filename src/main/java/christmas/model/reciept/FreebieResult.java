package christmas.model.reciept;

import christmas.model.menu.Menu;
import java.util.EnumMap;

public class FreebieResult {
    private final EnumMap<Menu, Integer> freebies;

    private FreebieResult(EnumMap<Menu, Integer> freebies) {
        this.freebies = freebies;
    }

    public static FreebieResult from(EnumMap<Menu, Integer> freebies) {
        return new FreebieResult(freebies);
    }

    public EnumMap<Menu, Integer> getFreebies() {
        return freebies;
    }
}
