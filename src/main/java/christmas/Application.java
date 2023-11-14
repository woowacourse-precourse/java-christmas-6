package christmas;

import christmas.controller.EventController;

public class Application {

    private final EventController eventController;

    public Application(final AppConfig appConfig) {
        this.eventController = appConfig.eventController;
    }

    public static void main(String[] args) {
        Application application = new Application(AppConfig.getInstance());
        application.run();
    }

    public void run() {
        eventController.initEvent();
    }
}
