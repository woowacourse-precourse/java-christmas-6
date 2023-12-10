package christmas.view;

import christmas.model.menu.Menu;
import christmas.model.order.MenuCount;
import christmas.model.promotion.Promotion;
import christmas.view.dto.ReceiptResponseDto;
import java.util.EnumMap;

public class OutputView {
    private static final String OUTPUT_WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String OUTPUT_REQUEST_REENTER_ORDER_INPUT = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String OUTPUT_REQUEST_REENTER_VISIT_DAY_INPUT = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String OUTPUT_PREVIEW_PROMOTION_BENEFITS_MESSAGE = "%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String OUTPUT_ITEM_QUANTITY_FORMAT = "%s %,d개\n";
    private static final String OUTPUT_PROMOTION_DETAIL_FORMAT = "%s: -%,d원\n";
    private static final String OUTPUT_AMOUNT_FORMAT = "%,d원\n";
    private static final String OUTPUT_NONE = "없음";


    public void printWelcomeMessage() {
        System.out.println(OUTPUT_WELCOME_MESSAGE);
    }

    public void printRequestReenterVisitDay() {
        System.out.println(OUTPUT_REQUEST_REENTER_VISIT_DAY_INPUT);
    }

    public void printRequestReenterOrder() {
        System.out.println(OUTPUT_REQUEST_REENTER_ORDER_INPUT);
    }

    public void printPromotionBenefits(ReceiptResponseDto responseDto) {
        System.out.printf((OUTPUT_PREVIEW_PROMOTION_BENEFITS_MESSAGE), responseDto.visitMonth(),
                responseDto.visitDay());

        showOrders(responseDto.orders());
        showTotalAmountBeforeDiscount(responseDto.totalAmountBeforeDiscount());
        showFreebies(responseDto.freebies());
        showBenefits(responseDto.benefitRecords());
        showTotalBenefitAmount(responseDto.totalBenefitAmount());
        showEstimatedPaymentAmount(responseDto.estimatedPaymentAmount());
        showEventBadge(responseDto.badgeName());
    }

    private void showOrders(EnumMap<Menu, MenuCount> orders) {
        System.out.println("\n<주문 메뉴>");

        orders.forEach((key, value) -> System.out.printf(OUTPUT_ITEM_QUANTITY_FORMAT,
                key.getKorean(),
                value.getCount()));
    }

    private void showTotalAmountBeforeDiscount(int amount) {
        System.out.println("\n<할인 전 총주문 금액>");

        System.out.printf(OUTPUT_AMOUNT_FORMAT, amount);
    }

    private void showFreebies(EnumMap<Menu, Integer> freebies) {
        System.out.println("\n<증정 메뉴>");
        freebies.forEach((menu, count) -> System.out.printf(OUTPUT_ITEM_QUANTITY_FORMAT, menu.getKorean(), count));

        // TODO : 검증 로직 옮기기
        if (freebies.isEmpty()) {
            System.out.println(OUTPUT_NONE);
        }
    }

    private void showBenefits(EnumMap<Promotion, Double> benefitRecords) {
        System.out.println("\n<혜택 내역>");
        benefitRecords.forEach(
                (promotion, amount) -> System.out.printf(OUTPUT_PROMOTION_DETAIL_FORMAT,
                        promotion.getDescription(), amount.intValue()));

        // TODO : 검증 로직 옮기기
        if (benefitRecords.isEmpty()) {
            System.out.println(OUTPUT_NONE);
        }
    }

    private void showTotalBenefitAmount(int amount) {
        System.out.println("\n<총혜택 금액>");

        System.out.printf(OUTPUT_AMOUNT_FORMAT, -amount);
    }

    private void showEstimatedPaymentAmount(int amount) {
        System.out.println("\n<할인 후 예상 결제 금액>");

        System.out.printf(OUTPUT_AMOUNT_FORMAT, amount);
    }

    private void showEventBadge(String badgeName) {
        System.out.println("\n<12월 이벤트 배지>");

        System.out.print(badgeName);
    }
}
