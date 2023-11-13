package christmas.controller;

import christmas.domain.Customer;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class PlannerController {
    private PlannerService plannerService = new PlannerService();

    public void run() {
        Customer customer = dateInit();
    }

    private Customer dateInit() {
        OutputView.printStartMessageOutputMessage();
        try {
            int date = InputView.printVisitedDateInputMessage();
            return plannerService.setVisitedDate(date);
        } catch (IllegalArgumentException e) {
            return dateInit();
        }
    }
}
