package christmas.systems.reservationsystem;

import static christmas.enums.events.NoEvent.NO_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.systems.ordersystem.OrderSystem;
import christmas.views.Messages;
import christmas.views.OutputView;
import java.time.LocalDate;
import java.util.List;

public class ReservationSystem {
    private final OrderSystem orderSystem;

    public ReservationSystem(OrderSystem orderSystem) {
        this.orderSystem = orderSystem;
    }

    public Receipt calculateOrderResult(LocalDate reservationDate, Orders orders) {
        return orderSystem.calculateOrderResult(reservationDate, orders);
    }

    public static void printResult(String restaurantName, LocalDate reservationDate, Orders orders, Receipt receipt) {
        printBenefit(restaurantName,reservationDate);
        printOrders(orders);
        printAmountBeforeDiscount(receipt);
        printGiftBenefit(receipt);
        printEventBenefits(receipt);
        printDiscountBenefit(receipt);
        printAfterDiscount(receipt);
        printBadge(reservationDate, receipt);
    }

    public static void printAskDate(String restaurantName, Integer month) {
        OutputView.printOut(Messages.announceHello(restaurantName, month));
        OutputView.printOut(Messages.askDate(month));
    }

    public static void printAskMenuAndQuantity() {
        OutputView.printOut(Messages.askMenuAndQuantity());
    }

    private static void printBenefit( String restaurantName, LocalDate reservationDate) {
        OutputView.printOut(Messages.announceEventBenefit(restaurantName, reservationDate));
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
        String oneEventResult = NO_EVENT.getName() + System.lineSeparator();
        if (receipt.isEligible()) {
            oneEventResult = Messages.perEventBenefit(oneEventResults);
        }
        OutputView.printOut(oneEventResult);
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

}
