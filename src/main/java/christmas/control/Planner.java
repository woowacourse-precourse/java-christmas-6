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
    private Item item;
    private Order order;
    private Event event;

    private List<Item> items = new ArrayList<>();
    private int date;

    public void action() {
        takeOrder();
        applyEvent();
        printOrder();
    }

    public void takeOrder() {
        takeDate();
        takeItem();
        order = Order.getInstance(items, date);
    }

    public void takeDate() {
        try {
            String input = inputView.readDate();
            Validation.validDate(input);

            date = Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            takeDate();
        }
    }

    public void takeItem() {
        boolean isComplete = false;
        String input = inputView.readItem();
        isComplete = Validation.validItems(input);

        if(!isComplete) {
            takeItem();
            return;
        }
        items = convertItem(input);
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

    }

    public void printOrder() {

    }
}
