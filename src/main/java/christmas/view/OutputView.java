package christmas.view;

import christmas.model.Discount;
import christmas.model.Menu;

import java.util.Map;

public class OutputView {
    private static final String eventTitle = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private static final String orderMenuTitle = "\n<주문 메뉴>";
    private static final String beforeDiscountTitle = "\n<할인 전 총주문 금액>";
    private static final String giftTitle = "\n<증정 메뉴>";
    private static final String discountMapTitle = "\n<혜택 내역>";
    private static final String discountPriceTitle = "\n<총혜택 금액>";
    private static final String totalPaymentTitle = "\n<할인 후 예상 결제 금액>";
    private static final String badgeTitle = "\n<12월 이벤트 배지>";

    public static void printEventTitle(Integer date) {
        System.out.printf(eventTitle, date);
    }

    public static void printOrderMenu(Map<Menu, Integer> orderMap) {
        System.out.println(orderMenuTitle);
        for(Map.Entry<Menu, Integer> entry : orderMap.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue().toString() + "개");
        }
    }

    public static void printBeforeDiscountTotalPrice(Integer totalPrice) {
        System.out.println(beforeDiscountTitle);
        System.out.printf("%,d원\n", totalPrice);
    }

    public static void printGift(Map<Discount, Integer> discountMap) {
        System.out.println(giftTitle);
        if(discountMap.get(Discount.GIFT_EVENT) != null) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public static void printDiscountMap(Map<Discount, Integer> discountMap) {
        System.out.println(discountMapTitle);
        for(Map.Entry<Discount, Integer> entry : discountMap.entrySet()) {
            System.out.printf("%s: -%,d원\n", entry.getKey().getDiscountName(), entry.getValue());
        }
        if(discountMap.isEmpty()) System.out.println("없음");
    }

    public static void printDiscountPrice(Integer discountPrice) {
        System.out.println(discountPriceTitle);
        if(discountPrice > 0) System.out.print("-");
        System.out.printf("%,d원\n", discountPrice);
    }

    public static void printTotalPayment(Integer totalPayment) {
        System.out.println(totalPaymentTitle);
        System.out.printf("%,d원\n", totalPayment);
    }

    public static void printBadge(String badge) {
        System.out.println(badgeTitle);
        System.out.println(badge);
    }
}
