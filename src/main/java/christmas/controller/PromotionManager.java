package christmas.controller;

import christmas.controller.dto.PromotionResult;
import christmas.controller.dto.PromotionsResult;
import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.Discount;
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
        int amount = printBeforePromotion(orders);
        Optional<PromotionsResult> result = applyPromotion(day, orders);
        printAfterPromotion(amount, result);
    }

    private int printBeforePromotion(Orders orders) {
        outputView.printMenu(orders);
        int amount = orders.calculateTotalPrice();
        outputView.printPrice(amount);
        return amount;
    }

    private void printAfterPromotion(int amount, Optional<PromotionsResult> result) {
        outputView.printGiftAndBenefit(result);
        int benefit = calculateTotalBenefit(result);
        outputView.printBenefitAmount(benefit);
        outputView.printFinalPrice(calculateFinalPrice(amount, result));
        outputView.printBadge(calculateBadge(benefit));
    }

    private Badge calculateBadge(int benefit) {
        return Badge.from(benefit);
    }

    private int calculateFinalPrice(int amount, Optional<PromotionsResult> result) {
        if (result.isEmpty()) {
            return amount;
        }
        int discount = (int) result.get().promotionResults()
                .stream()
                .map(PromotionResult::benefit)
                .filter(element -> element instanceof Discount)
                .mapToInt(element -> element.calculate())
                .sum();
        return amount - discount;
    }

    private int calculateTotalBenefit(Optional<PromotionsResult> result) {
        if (result.isEmpty()) {
            return 0;
        }
        return result.get().promotionResults()
                .stream()
                .mapToInt(element -> element.benefit().calculate())
                .sum();
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
