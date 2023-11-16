package christmas;

import christmas.promotion.controller.PromotionController;
import christmas.promotion.view.InputView;
import christmas.promotion.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        PromotionController promotionController = new PromotionController(inputView, outputView);
        promotionController.run();
    }
}
