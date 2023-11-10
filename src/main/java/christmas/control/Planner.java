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
        try {
            String input = inputView.readItem();
            Validation.validItem(input);

            items = convertItem(input);
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            takeItem();
        }
    }

    public List<Item> convertItem(String input) {
        List<Item> items = new ArrayList<>();
        String[] splitItems = input.split(",");

        for (String item : splitItems) {
            String food = item.substring(0, item.indexOf("-"));
            int count = Integer.parseInt(item.substring(item.indexOf("-") + 1));

            items.add(new Item(food, count));
        }
        return items;
    }

    public void applyEvent() {

    }

    public void printOrder() {

    }
}
