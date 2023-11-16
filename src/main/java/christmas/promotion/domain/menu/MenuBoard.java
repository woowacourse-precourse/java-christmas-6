package christmas.promotion.domain.menu;

import christmas.promotion.domain.event.discount.WeekdayDiscount;
import christmas.promotion.domain.event.discount.WeekendDiscount;
import christmas.promotion.exception.OrderMenuException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MenuBoard {

    private final List<EventfulMenu> menuBoard;

    public MenuBoard() {
        this.menuBoard = new ArrayList<>();
        initializeMenu();
    }

    public EventfulMenu findMenu(final String menuName) {
        return menuBoard.stream()
                .filter(menu -> menuName.equals(menu.getName()))
                .findFirst()
                .orElseThrow(OrderMenuException::new);
    }

    private void initializeMenu() {
        addAppetizer();
        addMainCourse();
        addDessert();
        addBeverage();
    }

    private void addAppetizer() {
        Arrays.stream(Appetizer.values())
                .map(EventfulMenu::createMenuItem)
                .forEach(menuBoard::add);
    }

    private void addMainCourse() {
        Arrays.stream(MainCourse.values())
                .map(value -> EventfulMenu.createMenuItemWithDiscount(value, Collections.singletonList(WeekendDiscount.INSTANCE)))
                .forEach(menuBoard::add);
    }

    private void addDessert() {
        Arrays.stream(Dessert.values())
                .map(value -> EventfulMenu.createMenuItemWithDiscount(value, Collections.singletonList(WeekdayDiscount.INSTANCE)))
                .forEach(menuBoard::add);
    }

    private void addBeverage() {
        Arrays.stream(Beverage.values())
                .map(EventfulMenu::createMenuItem)
                .forEach(menuBoard::add);
    }


    public List<EventfulMenu> getMenuBoard() {
        return menuBoard;
    }
}
