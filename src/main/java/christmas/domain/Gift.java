package christmas.domain;

import christmas.domain.constants.Menu;

public class Gift implements Benefit {
    private Menu menu;
    private int count;

    public Gift(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    @Override
    public int calculate() {
        return menu.getPrice() * count;
    }
}
