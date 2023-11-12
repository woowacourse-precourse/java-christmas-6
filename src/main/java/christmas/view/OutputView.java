package christmas.view;

import christmas.model.Event;
import christmas.model.GiftPrice;
import christmas.model.Item;
import christmas.model.Order;

import java.text.DecimalFormat;

public class OutputView {
    private final String UNIT = "원";
    private final String DECORATE = ": ";

    private DecimalFormat formatter = new DecimalFormat("###,###");
    private static OutputView outputView;

    private OutputView() {

    }

    public static OutputView getInstance() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public void printAll(Order order, Event event) {
        final String MONTH = "12월";
        System.out.println(MONTH + " " + order.getDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        printMenu(order);
        printAmount(order);
        printGift(event.presentGift(order.sumAmount()));
        printDiscount(event, order);
        printSumDiscount(event.getDiscountAmount());
        printPredictAmount(order, event);
        printBadge(event.presentBadge(event.getDiscountAmount()));
    }

    public void printMenu(Order order) {
        System.out.println("<주문 메뉴>");
        for (Item item : order.getItems()) {
            System.out.println(item.getFood() + " " + item.getCount() + "개");
        }
        System.out.println();
    }

    public void printAmount(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatter.format(order.sumAmount()) + UNIT);
        System.out.println();
    }

    public void printGift(String gift) {
        System.out.println("<증정 메뉴>");
        if (gift.equals("없음")) {
            System.out.println("없음\n");
            return;
        }
        System.out.println(gift + " 1개\n");
    }

    public void printDiscount(Event event, Order order) {
        final int MIN_PRICE = 10000;
        boolean isCanDiscount = false;
        System.out.println("<혜택 내역>");

        isCanDiscount = printChristmaxDiscount(event) || isCanDiscount;
        isCanDiscount = printWeekdayDiscount(order, event) || isCanDiscount;
        isCanDiscount = printWeekendDiscount(order, event) || isCanDiscount;
        isCanDiscount = printSpecialDiscount(event) || isCanDiscount;
        isCanDiscount = printGiftEvent(order, event) || isCanDiscount;

        if (!isCanDiscount) {
            System.out.println("없음");
        }
        System.out.println();
    }

    public boolean printChristmaxDiscount(Event event) {
        int discount = -event.discountD_DAY();
        if (Math.abs(discount) == 0) {
            return false;
        }
        System.out.println("크리스마스 디데이 할인" + DECORATE + formatter.format(discount) + UNIT);
        return true;
    }

    public boolean printWeekdayDiscount(Order order, Event event) {
        int discount = -event.discountWeekday(order.getDessertCount());
        if (Math.abs(discount) == 0) {
            return false;
        }
        System.out.println("평일 할인" + DECORATE + formatter.format(discount) + UNIT);
        return true;
    }

    public boolean printWeekendDiscount(Order order, Event event) {
        int discount = -event.discountWeekend(order.getMainCount());
        if (Math.abs(discount) == 0) {
            return false;
        }
        System.out.println("주말 할인" + DECORATE + formatter.format(discount) + UNIT);
        return true;
    }

    public boolean printSpecialDiscount(Event event) {
        int discount = -event.discountSpecial();
        if (Math.abs(discount) == 0) {
            return false;
        }
        System.out.println("특별 할인" + DECORATE + formatter.format(discount) + UNIT);
        return true;
    }

    public boolean printGiftEvent(Order order, Event event) {
        String gift = event.presentGift(order.sumAmount());
        int discount = -GiftPrice.valueOf(gift).getPrice();
        if (Math.abs(discount) == 0) {
            return false;
        }
        System.out.println("증정 이벤트" + DECORATE + formatter.format(discount) + UNIT);
        return true;
    }

    public void printSumDiscount(int discountAmount) {
        System.out.println("<총혜택 금액>");
        System.out.println(formatter.format(-discountAmount) + UNIT);
        System.out.println();
    }

    public void printPredictAmount(Order order, Event event) {
        final int AMOUNT = order.sumAmount();
        final int DISCOUNT_AMOUNT = event.getDiscountAmount();
        final int GIFT_PRICE = event.getGiftPrice(event.presentGift(order.sumAmount()));
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(formatter.format(AMOUNT - DISCOUNT_AMOUNT + GIFT_PRICE) + UNIT);
        System.out.println();
    }

    public void printBadge(String badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
        System.out.println();
    }
}