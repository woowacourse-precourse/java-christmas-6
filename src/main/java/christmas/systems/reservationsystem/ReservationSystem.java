package christmas.systems.reservationsystem;

import static christmas.enums.events.NoEvent.NO_EVENT;
import static christmas.enums.menu.NoMenu.NO_MENU;

import christmas.event.Gift;
import christmas.event.OneEventResult;
import christmas.order.Orders;
import christmas.order.Receipt;
import christmas.systems.ordersystem.OrderSystem;
import christmas.views.Messages;
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

    public static String printResult(String restaurantName, LocalDate reservationDate, Orders orders, Receipt receipt) {
        StringBuilder stringBuilder = new StringBuilder();
        String printBenefit = printBenefit(restaurantName, reservationDate);
        String printOrders = printOrders(orders);
        String printAmountBeforeDiscount = printAmountBeforeDiscount(receipt);
        String printGiftBenefit = printGiftBenefit(receipt);
        String printEventBenefits = printEventBenefits(receipt);
        String printDiscountBenefit = printDiscountBenefit(receipt);
        String printAfterDiscount = printAfterDiscount(receipt);
        String printBadge = printBadge(reservationDate, receipt);

        combineString(stringBuilder, printBenefit, printOrders, printAmountBeforeDiscount, printGiftBenefit,
                printEventBenefits,
                printDiscountBenefit, printAfterDiscount, printBadge);

        return stringBuilder.toString();
    }

    private static void combineString(StringBuilder stringBuilder, String printBenefit, String printOrders,
                                      String printAmountBeforeDiscount, String printGiftBenefit,
                                      String printEventBenefits,
                                      String printDiscountBenefit, String printAfterDiscount, String printBadge) {
        stringBuilder.append(printBenefit).append(System.lineSeparator());
        stringBuilder.append(printOrders).append(System.lineSeparator());
        stringBuilder.append(printAmountBeforeDiscount).append(System.lineSeparator()).append(System.lineSeparator());
        stringBuilder.append(printGiftBenefit).append(System.lineSeparator()).append(System.lineSeparator());
        stringBuilder.append(printEventBenefits).append(System.lineSeparator());
        stringBuilder.append(printDiscountBenefit).append(System.lineSeparator()).append(System.lineSeparator());
        stringBuilder.append(printAfterDiscount).append(System.lineSeparator()).append(System.lineSeparator());
        stringBuilder.append(printBadge).append(System.lineSeparator());
    }

    public static String printAskDate(String restaurantName, Integer month) {
        String announceHello = Messages.announceHello(restaurantName, month);
        String askDate = Messages.askDate(month);
        return announceHello + System.lineSeparator() + askDate;
    }

    public static String printAskMenuAndQuantity() {
        return Messages.askMenuAndQuantity();
    }

    private static String printBenefit(String restaurantName, LocalDate reservationDate) {
        return Messages.announceEventBenefit(restaurantName, reservationDate);
    }

    private static String printOrders(Orders orders) {
        String ordersAnnounce = Messages.announceOrders();
        String orderContext = Messages.repeatAllOrders(orders);
        return ordersAnnounce + System.lineSeparator() + orderContext;
    }

    private static String printAmountBeforeDiscount(Receipt receipt) {
        String announceBeforeDiscount = Messages.announceBeforeDiscount();
        Integer totalPriceBeforeDiscount = receipt.totalPriceBeforeDiscount();
        String showAmount = Messages.showAmount(totalPriceBeforeDiscount);
        return announceBeforeDiscount + System.lineSeparator() + showAmount;
    }

    private static String printGiftBenefit(Receipt receipt) {
        Gift gift = receipt.gift();
        String announceGift = Messages.announceGift();
        String giftResult = NO_MENU.getName();
        if (!receipt.isNoGift()) {
            giftResult = Messages.gift(gift.menuItem(), gift.quantity());
        }
        return announceGift + System.lineSeparator() + giftResult;
    }

    private static String printEventBenefits(Receipt receipt) {
        String announceEventBenefits = Messages.announceEventBenefits();
        List<OneEventResult> oneEventResults = receipt.oneEventResults();
        String oneEventResult = NO_EVENT.getName() + System.lineSeparator();
        if (receipt.isEligible()) {
            oneEventResult = Messages.perEventBenefit(oneEventResults);
        }
        return announceEventBenefits + System.lineSeparator() + oneEventResult;
    }

    private static String printDiscountBenefit(Receipt receipt) {
        String announceTotalDiscountBenefit = Messages.announceTotalDiscountBenefit();
        String showAmount = Messages.showAmount(-receipt.discountBenefit());
        return announceTotalDiscountBenefit + System.lineSeparator() + showAmount;
    }

    private static String printAfterDiscount(Receipt receipt) {
        String afterDiscountAmount = Messages.AfterDiscountAmount();
        String showAmount = Messages.showAmount(receipt.totalPriceBeforeDiscount() - receipt.discountBenefit());
        return afterDiscountAmount + System.lineSeparator() + showAmount;
    }

    private static String printBadge(LocalDate reservationDate, Receipt receipt) {
        String announceEventBadge = Messages.announceEventBadge(reservationDate.getMonthValue());
        String badgeName = receipt.badge().getName();
        return announceEventBadge + System.lineSeparator() + badgeName;
    }

}
