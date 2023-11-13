package christmas.controller;

import christmas.domain.Customer;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

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

    private void menuInit() {
        try {
            Map<String, Integer> menuMap = InputView.printMenuInitInputMessage();
            plannerService.setOrder(menuMap);
        } catch (IllegalArgumentException e) {
            menuInit();
        }
    }
}
