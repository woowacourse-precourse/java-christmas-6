package christmas.view.output;

import christmas.dto.BenefitDto;
import christmas.dto.OrderDto;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    private static final String PRINT_ORDER_RESULT_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String PRINT_TOTAL_ORDER_MESSAGE = "\n<주문 메뉴>";
    private static final String PRINT_TOTAL_ORDER = "%s %d개\n";
    private static final String PRINT_TOTAL_PRICE_MESSAGE = "\n<할인 전 총주문 금액>\n%s원\n";
    private static final String PRINT_GIFT_MENU_BENEFIT_MESSAGE = "\n<증정 메뉴>\n%s\n";
    private static final String PRINT_GIFT_MENU_BENEFIT_APPLICABLE = "샴페인 1개";
    private static final String PRINT_BENEFIT_NOT_APPLICABLE = "없음";
    private static final String PRINT_TOTAL_BENEFIT_MESSAGE = "\n<혜택 내역>";
    private static final String PRINT_TOTAL_BENEFIT = "%s: -%s원\n";
    private static final String PRINT_TOTAL_DISCOUNT_MESSAGE = "\n<총혜택 금액>";
    private static final String PRINT_TOTAL_DISCOUNT = "-%s원\n";
    private static final String PRINT_DISCOUNT_NOT_APPLICABLE = "%s원\n";
    private static final String PRINT_DISCOUNTED_TOTAL_PRICE_MESSAGE = "\n<할인 후 예상 결제 금액>\n%s원\n";
    private static final String PRINT_EVENT_BADGE_MESSAGE = "\n<12월 이벤트 배지>\n%s";
    private static final String PRINT_SNATA_EVENT_BADGE = "산타";
    private static final String PRINT_TREE_EVENT_BADGE = "트리";
    private static final String PRINT_STAR_EVENT_BADGE = "별";
    private static final DecimalFormat formatter = new DecimalFormat("###,###,###,###");

    public void printOrderResult(final int date) {
        System.out.printf(PRINT_ORDER_RESULT_MESSAGE, date);
    }

    public void printTotalMenu(final OrderDto orderDto) {
        final Map<String, Integer> order = orderDto.getOrder();
        System.out.println(PRINT_TOTAL_ORDER_MESSAGE);
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            final String menu = entry.getKey();
            final int quantity = entry.getValue();
            System.out.printf(PRINT_TOTAL_ORDER, menu,  quantity);
        }
    }

    public void printTotalPrice(final OrderDto orderDto) {
        final int totalPrice = orderDto.getTotalPrice();
        System.out.printf(PRINT_TOTAL_PRICE_MESSAGE, formatter.format(totalPrice));
    }

    public void printGiftMenuBenefit(final OrderDto orderDto) {
        final int totalPrice = orderDto.getTotalPrice();
        final String messageFormat = checkGiftMenuIsApplicable(totalPrice);
        System.out.printf(PRINT_GIFT_MENU_BENEFIT_MESSAGE, messageFormat);
    }

    private String checkGiftMenuIsApplicable(final int totalPrice) {
        if (totalPrice >= 120_000) {
            return PRINT_GIFT_MENU_BENEFIT_APPLICABLE;
        }
        return PRINT_BENEFIT_NOT_APPLICABLE;
    }

    public void printTotalBenefit(final BenefitDto benefitDto) {
        final Map<String, Integer> totalBenefit = benefitDto.getBenefit();
        System.out.println(PRINT_TOTAL_BENEFIT_MESSAGE);
        checkBenefitIsApplicable(totalBenefit);
    }

    private void checkBenefitIsApplicable(final Map<String, Integer> totalBenefit) {
        if (totalBenefit.isEmpty()) {
            System.out.print(PRINT_BENEFIT_NOT_APPLICABLE);
            return;
        }
        for (Map.Entry<String, Integer> entry : totalBenefit.entrySet()) {
            final String benefit = entry.getKey();
            final int discount = entry.getValue();
            System.out.printf(PRINT_TOTAL_BENEFIT, benefit,  formatter.format(discount));
        }
    }

    public void printTotalDiscount(final BenefitDto benefitDto) {
        final int totalDiscount = benefitDto.getTotalDiscount();
        System.out.println(PRINT_TOTAL_DISCOUNT_MESSAGE);
        final String messageFormat = checkDiscountIsApplicable(totalDiscount);
        System.out.printf(messageFormat, formatter.format(totalDiscount));
    }

    private String checkDiscountIsApplicable(final int totalDiscount) {
        if (totalDiscount > 0) {
            return PRINT_TOTAL_DISCOUNT;
        }
        return PRINT_DISCOUNT_NOT_APPLICABLE;
    }

    public void printDiscountedTotalPrice(final BenefitDto benefitDto) {
        final int DiscountTotalPrice = benefitDto.getDiscountedTotalPrice();
        System.out.printf(PRINT_DISCOUNTED_TOTAL_PRICE_MESSAGE, formatter.format(DiscountTotalPrice));
    }

    public void printEventBadge(final BenefitDto benefitDto) {
        final int totalDiscount = benefitDto.getTotalDiscount();
        System.out.printf(PRINT_EVENT_BADGE_MESSAGE, selectEventBadge(totalDiscount));
    }

    private String selectEventBadge(final int totalDiscount) {
        if (20_000 <= totalDiscount) { return PRINT_SNATA_EVENT_BADGE; }
        if (10_000 <= totalDiscount) { return PRINT_TREE_EVENT_BADGE; }
        return PRINT_STAR_EVENT_BADGE;
    }
}
