package christmas.controller;

import christmas.constant.errorMessage.exception.CustomArrayIndexOutOfBoundsException;
import christmas.constant.errorMessage.exception.CustomIllegalArgumentException;
import christmas.constant.errorMessage.exception.CustomIllegalStateException;
import christmas.constant.errorMessage.exception.CustomNullPointException;
import christmas.constant.errorMessage.exception.CustomNumberFormatException;
import christmas.dto.BenefitDto;
import christmas.dto.OrderDto;
import christmas.service.EventService;
import christmas.view.input.InputView;
import christmas.view.output.OutputView;
import java.util.Map;

public class EventController {

    private final OutputView outputView;
    private final InputView inputView;
    private final EventService eventService;

    public EventController(final EventService eventService, final InputView inputView, final OutputView outputView) {
        this.eventService = eventService;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void initEvent() {
        initDate();
        initOrder();
        initBenefit();
    }

    private void initDate() {
        try {
            final int date = inputView.readDate();
            eventService.initDate(date);
        } catch (CustomNumberFormatException | CustomNullPointException |
                 CustomIllegalArgumentException e) {
            System.out.println(e.getMessage());
            initDate();
        }
    }

    private void initOrder() {
        try {
            final Map<String, Integer> menus = inputView.readOrder();
            eventService.initOrder(menus);
        } catch (CustomNumberFormatException | CustomNullPointException |
                CustomIllegalArgumentException | CustomIllegalStateException |
                CustomArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            initOrder();
        }
    }

    private void initBenefit() {
        eventService.initBenefit();
    }

    public void playEvent() {
        announceOrderResult();
        announceTotalOrder();
        announceTotalBenefit();
    }

    private void announceOrderResult() {
        final int date = eventService.getEventDate();
        outputView.printOrderResult(date);
    }

    private void announceTotalOrder() {
        final OrderDto orderDto = eventService.getOrderDto();
        outputView.printTotalMenu(orderDto);
        outputView.printTotalPrice(orderDto);
        outputView.printGiftMenuBenefit(orderDto);
    }

    private void announceTotalBenefit() {
        final BenefitDto benefitDto = eventService.getBenefitDto();
        outputView.printTotalBenefit(benefitDto);
        outputView.printTotalDiscount(benefitDto);
        outputView.printDiscountedTotalPrice(benefitDto);
        outputView.printEventBadge(benefitDto);
    }
}
