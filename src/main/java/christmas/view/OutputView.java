package christmas.view;

import christmas.type.BadgeType;
import christmas.type.TextType;

import java.util.HashMap;
import java.util.Map;

public class OutputView {

    public static final String ENTER = "\n";
    public static final String SPACE = " ";
    public static final String COUNT = "개";
    public static final String WON = "원";
    public static final String PRESENT = "샴페인 1개";
    public static final String COLON = ":";
    public static final String MINUS = "-";

    public static void printStartMessageOutputMessage() {
        System.out.println(TextType.START_MESSAGE.getText());
    }

    public static void printPreviewOutputMessage(int date) {
        System.out.println("12월 " + date + TextType.SHOW_BENEFIT_OUTPUT_MESSAGE.getText() + ENTER);
    }

    public static void printOrderMenuOutputMessage(HashMap<String, Integer> orderMap) {
        System.out.println(TextType.ORDER_MENU_OUTPUT_MESSAGE.getText());
        for (String key : orderMap.keySet()) {
            System.out.println(key + SPACE + orderMap.get(key) + COUNT);
        }
    }

    public static void printTotalPriceMessage(int totalPrice) {
        System.out.println(TextType.BEFORE_DISCOUNT_ORDER_PRICE.getText());
        System.out.printf("%,d" + WON + ENTER, totalPrice);
    }

    public static void printBenefitsOutputMessage(Map<String, Integer> discountMap) {
        System.out.println(TextType.BENEFIT_HISTORY.getText());
        if (discountMap.size() == 0) {
            System.out.println(TextType.NONE.getText());
            return;
        }

        for (String key : discountMap.keySet()) {
            String formattedPrice = String.format(MINUS + "%,d원", discountMap.get(key));
            System.out.println(key + COLON + SPACE  + formattedPrice);
        }
    }

    public static void printPresentOutputMessage(boolean isGiven) {
        String menu = TextType.NONE.getText();
        if (isGiven) {
            menu = PRESENT;
        }
        System.out.println(TextType.PRESENTATION_MENU);
        System.out.println(menu);
    }

    public static void printTotalDiscountPriceOutputMessage(int totalDiscount) {
        System.out.println(TextType.TOTAL_BENEFIT_PRICE.getText());
        String formattedPrice = String.format(MINUS + "%,d" + WON + ENTER, totalDiscount);
        if (totalDiscount == 0) {
            formattedPrice = String.format("%d" + WON + ENTER, totalDiscount);
        }
        System.out.println(formattedPrice);
    }

    public static void printResultPriceOutputMessage(int price) {
        System.out.println(TextType.AFTER_DISCOUNT_PRICE.getText());
        System.out.printf("%,d" + WON + ENTER, price);
    }

    public static void printBadgeOutputMessage(int totalDiscount) {
        System.out.println(TextType.EVENT_BADGE.getText());
        if (totalDiscount >= BadgeType.SANTA.getPrice()) {
            System.out.println(BadgeType.SANTA.getBadgeName());
            return;
        }
        if (totalDiscount >= BadgeType.TREE.getPrice()) {
            System.out.println(BadgeType.TREE.getBadgeName());
            return;
        }
        if (totalDiscount >= BadgeType.STAR.getPrice()) {
            System.out.println(BadgeType.STAR.getBadgeName());
            return;
        }
        System.out.println(TextType.NONE.getText());
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
