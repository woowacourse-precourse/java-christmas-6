package christmas.control;

import christmas.model.Event;
import christmas.model.Item;
import christmas.model.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Planner {
    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();
    private Order order;
    private Event event;

    public void action() {
        takeOrder();
        applyEvent();
        printOrder();
    }

    public void takeOrder() {
        order = new Order(takeDate(), takeItem());
    }

    public int takeDate() {
        boolean isComplete = false;
        String input = inputView.readDate();
        isComplete = Validation.validDate(input);

        if (!isComplete) {
            return takeDate();
        }
        return Integer.parseInt(input);
    }

    public List<Item> takeItem() {
        boolean isComplete = false;
        String input = inputView.readItem();
        isComplete = Validation.validItems(input);

        if (!isComplete) {
            return takeItem();
        }
        return convertItem(input);
    }

    public List<Item> convertItem(String input) {
        List<Item> items = new ArrayList<>();
        String[] splitItems = input.split(",");

        for (String item : splitItems) {
            items.add(Item.extractItem(item));
        }
        return items;
    }

    public void applyEvent() {
        event = new Event(order.getDate());
        event.applyAll(order);
    }

    public void printOrder() {
        outputView.printAll(order, event);
    }
}
