package christmas.systems;

import static christmas.enums.events.NoEvent.NO_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.exceptions.RestaurantException;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.utils.StringToDateParser;
import christmas.utils.StringToOrdersParser;
import christmas.views.InputView;
import christmas.views.Messages;
import christmas.views.OutputView;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Function;

public class ReservationSystem {
    private final static Integer YEAR = 2023;
    private final static Integer MONTH = Month.DECEMBER.getValue();
    private final static String RESTAURANT_NAME = "우테코 식당";
    private final OrderSystem orderSystem;

    public ReservationSystem(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    public void process() {
        printAskDate();
        LocalDate reservationDate = getInputAndCatchException(
                input -> StringToDateParser.makeReservation(YEAR, MONTH, input));

        printAskMenuAndQuantity();
        Orders orders = getInputAndCatchException(StringToOrdersParser::parseInputToOrderSet);
        Receipt receipt = orderSystem.calculateOrderResult(reservationDate, orders);
        printResult(reservationDate, orders, receipt);
        InputView.close();
    }

    private static void printResult(LocalDate reservationDate, Orders orders, Receipt receipt) {
        printBenefit(reservationDate);
        printOrders(orders);
        printAmountBeforeDiscount(receipt);
        printGiftBenefit(receipt);
        printEventBenefits(receipt);
        printDiscountBenefit(receipt);
        printAfterDiscount(receipt);
        printBadge(reservationDate, receipt);
    }

    private static void printAskDate() {
        OutputView.printOut(Messages.announceHello(RESTAURANT_NAME, MONTH));
        OutputView.printOut(Messages.askDate(MONTH));
    }

    private static void printAskMenuAndQuantity() {
        OutputView.printOut(Messages.askMenuAndQuantity());
    }

    private static void printBenefit(LocalDate reservationDate) {
        OutputView.printOut(Messages.announceEventBenefit(RESTAURANT_NAME, reservationDate));
        OutputView.printOut("");
    }

    private static void printOrders(Orders orders) {
        OutputView.printOut(Messages.announceOrders());
        OutputView.printOut(Messages.repeatAllOrders(orders));
    }

    private static void printAmountBeforeDiscount(Receipt receipt) {
        OutputView.printOut(Messages.announceBeforeDiscount());
        Integer totalPriceBeforeDiscount = receipt.totalPriceBeforeDiscount();
        OutputView.printOut(Messages.showAmount(totalPriceBeforeDiscount));
        OutputView.printOut("");
    }

    private static void printGiftBenefit(Receipt receipt) {
        Gift gift = receipt.gift();
        OutputView.printOut(Messages.announceGift());
        String giftResult = NO_MENU.getName();
        if (!receipt.isNoGift()) {
            giftResult = Messages.gift(gift.menuItem(), gift.quantity());
        }
        OutputView.printOut(giftResult);
        OutputView.printOut("");
    }

    private static void printEventBenefits(Receipt receipt) {
        OutputView.printOut(Messages.announceEventBenefits());
        List<OneEventResult> oneEventResults = receipt.oneEventResults();
        String oneEventResult = NO_EVENT.getName();
        if(receipt.isEligible()){
            oneEventResult = Messages.perEventBenefit(oneEventResults);
        }
        OutputView.printOut(oneEventResult);
        OutputView.printOut("");
    }

    private static void printDiscountBenefit(Receipt receipt) {
        OutputView.printOut(Messages.announceTotalDiscountBenefit());
        OutputView.printOut(Messages.showAmount(-receipt.discountBenefit()));
        OutputView.printOut("");
    }

    private static void printAfterDiscount(Receipt receipt) {
        OutputView.printOut(Messages.AfterDiscountAmount());
        OutputView.printOut(Messages.showAmount(receipt.totalPriceBeforeDiscount() - receipt.discountBenefit()));
        OutputView.printOut("");
    }

    private static void printBadge(LocalDate reservationDate, Receipt receipt) {
        OutputView.printOut(Messages.announceEventBadge(reservationDate.getMonthValue()));
        OutputView.printOut(receipt.badge().getName());
    }

    private <T> T getInputAndCatchException(Function<String, T> processor) {
        while (true) {
            try {
                String input = InputView.readLine();
                return processor.apply(input);
            } catch (RestaurantException e) {
                OutputView.printException(e);
            }
        }
    }
}
