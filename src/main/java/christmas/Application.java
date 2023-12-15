package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.PromotionManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PromotionManager promotionManager = new PromotionManager(
                new InputView(),
                new OutputView()
        );
        promotionManager.run();
        Console.close();
    }
}
