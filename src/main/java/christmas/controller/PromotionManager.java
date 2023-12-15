package christmas.controller;

import christmas.controller.dto.PromotionResult;
import christmas.controller.dto.PromotionsResult;
import christmas.domain.Benefit;
import christmas.domain.Orders;
import christmas.domain.constants.Promotion;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PromotionManager {
    private final InputView inputView;
    private final OutputView outputView;

    public PromotionManager(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.outputView.start();
    }

    public void run() {
        int day = inputView.readDay();
        Orders orders = inputView.readOrders();

        outputView.printMenuAndPrice(orders);
        Optional<PromotionsResult> result = applyPromotion(day, orders);
        outputView.printResult(result);
    }

    private Optional<PromotionsResult> applyPromotion(int day, Orders orders) {
        if (orders.calculateTotalPrice() > 10000) {
            return Optional.of(
                    getPromotionResult(day, orders)
            );
        }
        return Optional.empty();
    }

    private PromotionsResult getPromotionResult(int day, Orders orders) {
        List<PromotionResult> promotionResults = new ArrayList<>();
        for (Promotion promotion : Promotion.values()) {
            Optional<Benefit> benefit = promotion.getInstance().apply(day, orders);
            if (benefit.isPresent()) {
                promotionResults.add(
                        new PromotionResult(
                                promotion,
                                benefit.get()
                        )
                );
            }
        }
        return new PromotionsResult(promotionResults);
    }
}
