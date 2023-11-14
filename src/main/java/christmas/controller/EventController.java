package christmas.controller;

import christmas.service.EventService;
import christmas.view.input.InputView;
import java.util.Map;

public class EventController {

    private final InputView inputView;
    private final EventService eventService;

    public EventController(final EventService eventService, final InputView inputView) {
        this.eventService = eventService;
        this.inputView = inputView;
    }

    public void run() {

    }

    public void initEvent() {
        initDate();
        initMenu();
    }

    private void initDate() {
        final int date = inputView.readDate();
        eventService.initDate(date);
    }

    private void initMenu() {
        final Map<String, Integer> menus = inputView.readOrder();
        eventService.initMenu(menus);
    }
}
