package christmas;

import christmas.controller.EventController;
import christmas.service.EventService;
import christmas.view.input.InputView;
import christmas.view.input.reader.DefaultReader;
import christmas.view.input.reader.Reader;
import christmas.view.output.OutputView;

public class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();

    public final EventController eventController;
    public final EventService eventService;
    public final OutputView outputView;
    public final InputView inputView;
    public final DefaultReader reader;

    private AppConfig() {
        this.reader = initReader();
        this.inputView = initInputView(reader);
        this.outputView = initOutputView();
        this.eventService = initEventService();
        this.eventController = initEventController(eventService, inputView, outputView);
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

    private OutputView initOutputView() {
        return new OutputView();
    }

    private EventService initEventService() {
        return new EventService();
    }

    private EventController initEventController(final EventService eventService,
                                                final InputView inputView,
                                                final OutputView outputView) {
        return new EventController(eventService, inputView, outputView);
    }
}
