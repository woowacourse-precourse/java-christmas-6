package christmas;

import christmas.domain.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.badge.Badge;
import christmas.domain.benefit.Benefits;
import christmas.exception.handler.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class PromotionController {
    public void run(){
        OutputView.sayHello();
        PromotionDate visitDay = RetryHandler.getOrRetry(() -> getVisitDay(),
                "유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        Orders orders = RetryHandler.getOrRetry(() -> getOrders(),
                "유효하지 않은 주문입니다. 다시 입력해 주세요.");
        Benefits benefits = getBenefits(visitDay, orders);
        //todo 혜택 금액의 - 처리
        Badge badge = Badge.of(benefits.getTotalBenefitPrice() * -1);

        printResult(visitDay, orders);
        printBenefits(orders, benefits);
        printBadge(badge);
    }

    private void printBadge(Badge badge) {
        OutputView.printBadge(badge);
    }

    private void printBenefits(Orders orders, Benefits benefits) {
        OutputView.printGifts(benefits);
        OutputView.printBenefits(benefits);
        OutputView.printBenefitPrice(benefits);
        OutputView.printDiscountPrice(orders.getTotalPrice() + benefits.calcDiscountPrice());
    }

    private void printResult(PromotionDate visitDay, Orders orders) {
        OutputView.printResultHead(visitDay);
        OutputView.printOrders(orders);
        OutputView.printNoBenefitTotalPrice(orders);
    }

    public PromotionDate getVisitDay(){
        int visitDay = InputView.getVisitDay();
        return new PromotionDate(visitDay);
    }

    public Orders getOrders(){
        List<String> orders = InputView.getMenus();
        return Orders.from(orders);
    }

    public Benefits getBenefits(PromotionDate visitDay, Orders orders){
        return Benefits.from(visitDay, orders);
    }
}
