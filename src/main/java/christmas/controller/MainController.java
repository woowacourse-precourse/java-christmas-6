package christmas.controller;

import christmas.model.order.Order;
import christmas.model.order.VisitDate;
import christmas.model.promotion.PromotionManager;
import christmas.model.promotion.freebie.freebiePolicy.ChampagneFreebiePolicy;
import christmas.model.reciept.PromotionResult;
import christmas.model.reciept.Receipt;
import christmas.util.mapper.DtoModelMapper;
import christmas.util.validator.InputOrderValidator;
import christmas.util.validator.InputVisitDayValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.dto.OrderRequestDto;
import christmas.view.dto.VisitDayRequestDto;
import java.util.List;

public class MainController {
    private final OutputView outputView;
    private final InputView inputView;

    public MainController(OutputView outputView, InputView inputView) {
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void run() {
        outputView.printWelcomeMessage();
        VisitDate visitDate = getValidVisitDate();
        Order order = getValidOrder(visitDate);

        Receipt receipt = Receipt.issue(order, calculatePromotionResult(order));
        outputView.printPromotionBenefits(DtoModelMapper.ReceiptToDto(receipt));
    }

    private VisitDate getValidVisitDate() {
        while (true) {
            try {
                VisitDayRequestDto requestDto = inputView.readVisitDay();
                InputVisitDayValidator.validateVisitDay(requestDto.day());

                return DtoModelMapper.dtoToVisitDate(requestDto);
            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
                outputView.printRequestReenterVisitDay();
            }
        }
    }

    private Order getValidOrder(VisitDate visitDate) {
        while (true) {
            try {
                OrderRequestDto requestDto = inputView.readOrder();
                InputOrderValidator.validateOrder(requestDto.order());

                return DtoModelMapper.dtoToOrder(visitDate, requestDto);
            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
                outputView.printRequestReenterOrder();
            }
        }
    }

    private PromotionResult calculatePromotionResult(Order order) {
        PromotionManager promotionManager = new PromotionManager(
                List.of(new ChampagneFreebiePolicy())
        );
        return promotionManager.getResult(order);
    }
}