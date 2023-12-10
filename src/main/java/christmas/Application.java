package christmas;

import christmas.controller.MainController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        MainController mainController = new MainController(new OutputView(), new InputView());
        mainController.run();
    }
}
