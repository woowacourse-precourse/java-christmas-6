package christmas;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.exception.handler.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class PromotionController {
    public void run(){
        OutputView.sayHello();
        VisitDate visitDay = RetryHandler.getOrRetry(() -> getVisitDay());
        Orders orders = RetryHandler.getOrRetry(() -> getOrders());

        printResult(visitDay, orders);
    }

    private void printResult(VisitDate visitDay, Orders orders) {
        OutputView.printResultHead(visitDay);
        OutputView.printOrders(orders);
        OutputView.printNoBenefitTotalPrice(orders);
    }

    public VisitDate getVisitDay(){
        int visitDay = InputView.getVisitDay();
        return new VisitDate(visitDay);
    }

    public Orders getOrders(){
        List<String> orders = InputView.getMenus();
        return new Orders(orders);
    }
}
