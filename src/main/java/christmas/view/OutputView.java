package christmas.view;

import christmas.model.EventBadge;
import christmas.model.Menu;
import christmas.model.Promotion;
import christmas.model.Reservation;

import java.time.LocalDate;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map;

public class OutputView {
    public static final String WELCOME_STATE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    public static final String NOTHING_STATE = "없음";
    public static final String DEFAULT_PRESENT = "샴페인";

    public void welcome () {
        System.out.println(WELCOME_STATE);
    }

    public void notifyReservationPreview (Reservation reservation) {
        LocalDate reservedDate = reservation.checkReservedDate();
        String reservationDate = String.format("%d월 %d일", reservedDate.getMonthValue(), reservedDate.getDayOfMonth());

        System.out.println(reservationDate + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void previewOrderedMenu (Reservation reservation) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        List<SimpleEntry<Menu, Integer>> orderedMenu =reservation.checkOrderedMenu();

        orderedMenu.forEach(e ->
                System.out.println(e.getKey() + " " +e.getValue() + "개"));

    }

    public void previewTotalAmountBeforeApplying (Reservation reservation) {
        System.out.println();
        System.out.println("<할인 전 총주문 금액>");
        String totalAmount =String.format("%,d원", reservation.checkBill());
        System.out.println(totalAmount);
    }

    public void notifyPresentMenu (Reservation reservation) {
        System.out.println();
        System.out.println("<증정 메뉴>");
        boolean isPresentPromotionApplied = reservation.checkPromotionContained(Promotion.PRESENT_PROMOTION);

        if (isPresentPromotionApplied) {
            System.out.println(DEFAULT_PRESENT + " 1개");
            return;
        }
        System.out.println(NOTHING_STATE);
    }

    public void notifyPromotionApplied (Reservation reservation) {
        System.out.println();
        System.out.println("<혜택 내역>");
        Map<Promotion, Integer> promotionsApplied = reservation.checkPromotionApplied();

        if (promotionsApplied.isEmpty()) {
            System.out.println(NOTHING_STATE);
            return;
        }

        for (Map.Entry<Promotion, Integer> promotionApplied : promotionsApplied.entrySet()) {
            String benefitApplied = String.format("%,d", promotionApplied.getValue());
            System.out.println(promotionApplied.getKey() + ": -" + benefitApplied + "원");
        }
    }

    public void notifyTotalBenefit (Reservation reservation) {
        System.out.println();
        System.out.println("<총혜택 금액>");

        int totalBenefit = reservation.checkAppliedPromotionBenefit();

        if (totalBenefit == 0) {
            System.out.println("0원");
            return;
        }

        System.out.printf("-%,d원", totalBenefit);
        System.out.println();
    }

    public void notifyPaymentAmountAfterPromotion (Reservation reservation){
        System.out.println();
        System.out.println("<할인 후 예상 결제 금액>");

        int totalAmount = reservation.checkBill();
        int totalBenefit = reservation.checkAppliedPromotionBenefit();

        if (reservation.checkPromotionContained(Promotion.PRESENT_PROMOTION)) {
            totalBenefit -= Promotion.PRESENT_PROMOTION.checkPromotionDefaultBenefit();
        }

        String paymentAmountAfterPromotion = String.format("%,d원", totalAmount - totalBenefit);

        System.out.println(paymentAmountAfterPromotion);
    }

    public void notifyEventBadge (Reservation reservation) {
        System.out.println();
        System.out.println("<12월 이벤트 배지>");

        EventBadge badge = reservation.checkEventBadge();

        if (badge == null) {
            System.out.println(NOTHING_STATE);
            return;
        }
        System.out.print(badge);
    }
}
