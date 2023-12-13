package christmas.domain;

import christmas.domain.menu.Menu;
import christmas.domain.menu.MenuAndCount;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Orders {
    private final List<MenuAndCount> orders;

    public Orders(List<String> orders) {
        this.orders = orders.stream()
                .map(MenuAndCount::from)
                .toList();
    }
}
