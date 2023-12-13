package christmas.view;

import christmas.domain.PromotionDate;
import christmas.domain.badge.Badge;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.Benefits;
import christmas.domain.menu.Orders;
import christmas.view.io.Printer;
import java.text.DecimalFormat;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";
    public static final String NONE_LIST = "없음";
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("###,##0");

    public static void printException(String exceptionMessage) {
        Printer.printMessage(EXCEPTION_PREFIX + exceptionMessage);
    }

    public static void newLine() {
        Printer.printMessage("");
    }

    public static void sayHello() {
        Printer.printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printResultHead(PromotionDate promotionDate) {
        Printer.printMessageUsingFormat("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", promotionDate.getDayOfMonth());
    }

    public static void printOrders(Orders orders) {
        Printer.printMessage("<주문 메뉴>");
        orders.getOrders().forEach(
                menuAndCount ->
                        Printer.printMessageUsingFormat("%s %d개", menuAndCount.getMenuName(), menuAndCount.getCount())
        );
        newLine();
    }


    public static void printNoBenefitTotalPrice(Orders orders) {
        Printer.printMessage("<할인 전 총주문 금액>");
        Printer.printMessageUsingFormat("%s원", MONEY_FORMAT.format(orders.calcTotalPrice()));
        newLine();
    }

    public static void printBenefits(Benefits benefits) {
        Printer.printMessage("<혜택 내역>");
        if (benefits.hasNoBenefits()) {
            Printer.printMessage(NONE_LIST);
            newLine();
            return;
        }
        benefits.getBenefits().forEach(OutputView::printEachBenefit);
        newLine();
    }

    private static void printEachBenefit(Benefit benefit) {
        Printer.printMessageUsingFormat("%s: %s원", benefit.getBenefitName(),
                MONEY_FORMAT.format(benefit.getBenefitPrice()));
    }

    public static void printBenefitPrice(Benefits benefits) {
        Printer.printMessage("<총혜택 금액>");
        Printer.printMessageUsingFormat("%s원", MONEY_FORMAT.format(benefits.calcTotalBenefitPrice()));
        newLine();
    }

    public static void printDiscountPrice(int discountedPrice) {
        Printer.printMessage("<할인 후 예상 결제 금액>");
        Printer.printMessageUsingFormat("%s원", MONEY_FORMAT.format(discountedPrice));
        newLine();
    }

    public static void printBadge(Badge badge) {
        Printer.printMessage("<12월 이벤트 배지>");
        Printer.printMessageUsingFormat("%s", badge.getBadgeName());
        newLine();
    }

    public static void printGifts(Benefits benefits) {
        Printer.printMessage("<증정 메뉴>");
        if (benefits.hasNoGift()) {
            Printer.printMessage(NONE_LIST);
            newLine();
            return;
        }
        benefits.getGifts().forEach(menuAndCount ->
                Printer.printMessageUsingFormat("%s %d개", menuAndCount.getMenuName(), menuAndCount.getCount()));
    }
}
