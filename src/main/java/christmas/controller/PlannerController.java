package christmas.controller;

import christmas.domain.Customer;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.HashMap;
import java.util.Map;

public class PlannerController {
    private PlannerService plannerService = new PlannerService();

    public void run() {
        Customer customer = dateInit();
        menuInit();
        showTotalPrice();
        showPresent();
        showBenefits(customer.getVisitedDate());
        showBenefitsContent(customer.getVisitedDate());
        showTotalDiscountPrice();
        showResultPrice();
        showEventBadge();
    }

    private void showEventBadge() {
        int totalDiscount = plannerService.getTotalDiscount();
        OutputView.printBadgeOutputMessage(totalDiscount);
    }

    private void showResultPrice() {
        int totalDiscount = plannerService.getTotalDiscount();
        int totalPrice = plannerService.getTotalPrice();
        OutputView.printResultPriceOutputMessage(totalPrice -  totalDiscount);
    }

    private void showTotalDiscountPrice() {
        int totalDiscount = plannerService.getTotalDiscount();
        OutputView.printTotalDiscountPriceOutputMessage(totalDiscount);
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
