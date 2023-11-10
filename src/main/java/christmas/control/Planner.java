package christmas.control;

import christmas.model.Event;
import christmas.model.Menu;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Planner {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private Menu menu;
    private Order order;
    private Event event;

    public void action() {
        takeOrder();
        applyEvent();
        printOrder();
    }

    public void takeOrder() {

    }

    public void applyEvent() {

    }

    public void printOrder() {

    }
}
