package christmas.view;

import christmas.domain.Orders;
import christmas.domain.PromotionDate;
import christmas.domain.benefit.Benefit;
import christmas.domain.benefit.Benefits;
import christmas.view.io.Printer;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final DecimalFormat MONEY_FORMAT = new DecimalFormat("###,##0");
    public static final String EXCEPTION_PREFIX = "[ERROR] ";

    public static void printException(Exception e) {
        Printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
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
        Printer.printMessageUsingFormat("%s원", MONEY_FORMAT.format(orders.getTotalPrice()));
        newLine();
    }

    public static void printBenefits(Benefits benefits) {
        Printer.printMessage("<혜택 내역>");
        if(benefits.hasNoBenefits()){
            Printer.printMessage("<없음>");
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
        Printer.printMessageUsingFormat("%s원", MONEY_FORMAT.format(benefits.getTotalBenefitPrice()));
        newLine();
    }

    public static void printDiscountPrice(int discountedPrice) {
        Printer.printMessage("<할인 금액>");
        Printer.printMessageUsingFormat("%s원", MONEY_FORMAT.format(discountedPrice));
        newLine();
    }
    private static <T> void printListUsingFormat(List<T> list) {
        list.forEach(t -> Printer.printMessageUsingFormat("FORMAT", 1, 2, 3));
    } //
}
