package christmas;

import christmas.controller.EventController;
import christmas.service.EventService;
import christmas.view.input.InputView;
import christmas.view.input.reader.DefaultReader;
import christmas.view.input.reader.Reader;

public class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();

    public final EventController eventController;
    public final EventService eventService;
    public final InputView inputView;
    public final DefaultReader reader;

    private AppConfig() {
        this.reader = initReader();
        this.inputView = initInputView(reader);
        this.eventService = initEventService();
        this.eventController = initEventController(eventService, inputView);
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

    private EventService initEventService() {
        return new EventService();
    }

    private EventController initEventController(final EventService eventService,
                                                final InputView inputView) {
        return new EventController(eventService, inputView);
    }
}
