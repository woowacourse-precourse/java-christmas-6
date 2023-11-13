package christmas.view;

import christmas.type.TextType;

import java.util.HashMap;

public class OutputView {

    public static final String ENTER = "\n";
    public static final String SPACE = " ";
    public static final String COUNT = "개";
    public static final String WON = "원";

    public static void printStartMessageOutputMessage() {
        System.out.println(TextType.START_MESSAGE.getText());
    }

    public static void printPreviewOutputMessage(int date) {
        System.out.println("12월 " + date + TextType.SHOW_BENEFIT_OUTPUT_MESSAGE + ENTER);
    }

    public static void printOrderMenuOutputMessage(HashMap<String, Integer> orderMap) {
        System.out.println(TextType.ORDER_MENU_OUTPUT_MESSAGE);
        for (String key : orderMap.keySet()) {
            System.out.println(key + SPACE + orderMap.get(key) + COUNT);
        }
    }

    public static void printTotalPriceMessage(int totalPrice) {
        System.out.println(TextType.BEFORE_DISCOUNT_ORDER_PRICE);
        System.out.printf("%,d" + WON + ENTER, totalPrice);
    }
}
