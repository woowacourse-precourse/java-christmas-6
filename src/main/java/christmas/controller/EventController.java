package christmas.controller;

import christmas.service.EventService;
import christmas.view.input.InputView;

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
    }

    private void initDate() {
        final int date = inputView.readDate();
        eventService.initDate(date);
    }
}
