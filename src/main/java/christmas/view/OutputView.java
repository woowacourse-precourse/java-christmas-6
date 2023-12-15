package christmas.view;

import christmas.controller.dto.PromotionResult;
import christmas.controller.dto.PromotionsResult;
import christmas.domain.Badge;
import christmas.domain.Benefit;
import christmas.domain.Gift;
import christmas.domain.Order;
import christmas.domain.Orders;
import christmas.view.console.ConsoleWriter;
import java.util.Optional;

public class OutputView {
    private static final String ORDER_MENU = "%s %s개";
    private static final String PRICE = "%,d원";
    private static final String DISCOUNT = "-%,d원";
    private static final String NONE = "없음";
    private static final String PROMOTION_RESULT = "%s: -%,d원";

    public void start() {
        ConsoleWriter.printlnMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu(Orders orders) {
        ConsoleWriter.printlnMessage("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");

        ConsoleWriter.printlnMessage("<주문 메뉴>");
        for (Order order : orders.getOrders()) {
            ConsoleWriter.printlnFormat(ORDER_MENU, order.getMenu().getName(), order.getCount());
        }
        ConsoleWriter.println();
    }

    public void printPrice(int amount) {
        ConsoleWriter.printlnMessage("<할인 전 총주문 금액>");
        ConsoleWriter.printlnFormat(PRICE, amount);
        ConsoleWriter.println();
    }

    public void printGiftAndBenefit(Optional<PromotionsResult> result) {
        printGiftMenu(result);
        ConsoleWriter.println();
        printBenefit(result);
        ConsoleWriter.println();
    }

    private void printGiftMenu(Optional<PromotionsResult> result) {
        ConsoleWriter.printlnMessage("<증정 메뉴>");
        if (result.isEmpty()) {
            ConsoleWriter.printlnMessage(NONE);
            return;
        }
        for (PromotionResult promotionResult : result.get().promotionResults()) {
            Benefit benefit = promotionResult.benefit();
            if (benefit instanceof Gift) {
                Gift gift = (Gift) benefit;
                ConsoleWriter.printlnFormat(ORDER_MENU, gift.getMenu().getName(), gift.getCount());
            }
        }
    }

    private void printBenefit(Optional<PromotionsResult> result) {
        ConsoleWriter.printlnMessage("<혜택 내역>");
        if (result.isEmpty()) {
            ConsoleWriter.printlnMessage(NONE);
            return;
        }
        for (PromotionResult promotionResult : result.get().promotionResults()) {
            ConsoleWriter.printlnFormat(
                    PROMOTION_RESULT,
                    promotionResult.promotion().getName(),
                    promotionResult.benefit().calculate()
            );
        }
    }

    public void printBenefitAmount(int amount) {
        ConsoleWriter.printlnMessage("<총 혜택 금액>");
        ConsoleWriter.printlnFormat(DISCOUNT, amount);
        ConsoleWriter.println();
    }

    public void printFinalPrice(int finalPrice) {
        ConsoleWriter.printlnMessage("<할인 후 예상 결제 금액>");
        ConsoleWriter.printlnFormat(PRICE, finalPrice);
        ConsoleWriter.println();
    }

    public void printBadge(Badge badge) {
        ConsoleWriter.printlnMessage("<12월 이벤트 배지>");
        if (badge == null) {
            ConsoleWriter.printlnMessage(NONE);
        }
        ConsoleWriter.printlnMessage(badge.getName());
    }
}
