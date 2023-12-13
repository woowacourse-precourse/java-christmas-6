package christmas;

import christmas.domain.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.BenefitFactory;
import christmas.exception.handler.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.List;

public class PromotionController {
    public void run(){
        OutputView.sayHello();
        PromotionDate visitDay = RetryHandler.getOrRetry(() -> getVisitDay());
        Orders orders = RetryHandler.getOrRetry(() -> getOrders());

        //todo 일급 컬렉션 사용하기
        List<Benefit> benefits = getBenefits(visitDay, orders);

        printResult(visitDay, orders);
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

    public List<Benefit> getBenefits(PromotionDate visitDay, Orders orders){
        return BenefitFactory.allPossibleBenefit(visitDay, orders);
    }
}
