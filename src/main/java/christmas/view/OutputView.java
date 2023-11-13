package christmas.view;

import christmas.type.TextType;

public class OutputView {

    public static final String ENTER = "\n";

    public static void printStartMessageOutputMessage() {
        System.out.println(TextType.START_MESSAGE.getText());
    }

    public static void printPreviewOutputMessage(int date) {
        System.out.println("12월 " + date + TextType.SHOW_BENEFIT_OUTPUT_MESSAGE + ENTER);
    }
}
