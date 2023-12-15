package christmas.domain;

import christmas.domain.constants.Menu;

public class Gift implements Benefit {
    private Menu menu;
    private int count;

    @Override
    public int calculate() {
        return menu.getPrice() * count;
    }
}
