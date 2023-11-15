package christmas.controller;

import christmas.domain.Customer;
import christmas.domain.Order;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class PlannerController {
    private PlannerService plannerService = new PlannerService(new Order());

    public void run() {
        Customer customer = dateInit();
        menuInit();
        showBenefits(customer.getVisitedDate());
        showTotalPrice();
        showPresent();
        showBenefitsContent(customer.getVisitedDate());
        showTotalBenefitPrice();
        showResultPrice();
        showEventBadge();
    }

    private void showEventBadge() {
        int totalBenefit = plannerService.getTotalBenefit();
        OutputView.printBadgeOutputMessage(totalBenefit);
    }

    private void showResultPrice() {
        int totalDiscount = plannerService.getTotalDiscount();
        int totalPrice = plannerService.getTotalPrice();
        OutputView.printResultPriceOutputMessage(totalPrice -  totalDiscount);
    }

    private void showTotalBenefitPrice() {
        int totalDiscount = plannerService.getTotalBenefit();
        OutputView.printTotalBenefitPriceOutputMessage(totalDiscount);
    }

    private void showPresent() {
        OutputView.printPresentOutputMessage(plannerService.isBiggerThanPricePresent());
    }

    private void showBenefitsContent(int date) {
        Map<String, Integer> discountMap = plannerService.calculateDiscount(date);
        OutputView.printBenefitsOutputMessage(discountMap);
    }

    private void showTotalPrice() {
        int totalPrice = plannerService.getTotalPrice();
        OutputView.printTotalPriceMessage(totalPrice);
    }

    private void showBenefits(int date) {
        OutputView.printPreviewOutputMessage(date);
        HashMap<String, Integer> orderMap = plannerService.getOrder();
        OutputView.printOrderMenuOutputMessage(orderMap);
    }

    private Customer dateInit() {
        OutputView.printStartMessageOutputMessage();
        try {
            int date = InputView.printVisitedDateInputMessage();
            return plannerService.setVisitedDate(date);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            return dateInit();
        }
    }

    private void menuInit() {
        try {
            Map<String, Integer> menuMap = InputView.printMenuInitInputMessage();
            plannerService.setOrder(menuMap);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e);
            menuInit();
        }
    }
}
