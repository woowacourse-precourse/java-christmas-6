package christmas;

import christmas.domain.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.benefit.Benefits;
import christmas.exception.handler.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class PromotionController {
    public void run(){
        OutputView.sayHello();
        PromotionDate visitDay = RetryHandler.getOrRetry(() -> getVisitDay());
        Orders orders = RetryHandler.getOrRetry(() -> getOrders());
        Benefits benefits = getBenefits(visitDay, orders);

        printResult(visitDay, orders);
        printBenefits(orders, benefits);

    }

    private void printBenefits(Orders orders, Benefits benefits) {
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
        return new Orders(orders);
    }

    public Benefits getBenefits(PromotionDate visitDay, Orders orders){
        return Benefits.from(visitDay, orders);
    }
}
