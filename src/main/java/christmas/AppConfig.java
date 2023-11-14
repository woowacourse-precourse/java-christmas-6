package christmas;

import christmas.controller.EventController;
import christmas.view.input.InputView;
import christmas.view.input.reader.DefaultReader;
import christmas.view.input.reader.Reader;

public class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();

    public final DefaultReader reader;
    public final InputView inputView;
    public final EventController eventController;

    private AppConfig() {
        this.reader = initReader();
        this.inputView = initInputView(reader);
        this.eventController = initEventController(inputView);
    }

    public static AppConfig getInstance() {
        return APP_CONFIG;
    }

    private DefaultReader initReader() {
        return new DefaultReader();
    }

    private InputView initInputView(final Reader reader) {
        return new InputView(reader);
    }

    private EventController initEventController(final InputView inputView) {
        return new EventController(inputView);
    }
}
