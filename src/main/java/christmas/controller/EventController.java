package christmas.controller;

import christmas.view.input.InputView;

public class EventController {

    private final InputView inputView;

    public EventController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        inputView.readDate();
    }
}
