package christmas;

import christmas.domain.VisitDate;
import christmas.exception.handler.RetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PromotionController {
    public void run(){
        OutputView.sayHello();
        VisitDate visitDay = RetryHandler.getOrRetry(() -> getVisitDay());

    }

    public VisitDate getVisitDay(){
        int visitDay = InputView.getVisitDay();
        return new VisitDate(visitDay);
    }
}
