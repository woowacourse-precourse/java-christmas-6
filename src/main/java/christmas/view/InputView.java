package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.type.TextType;
import christmas.utils.Parser;

import java.util.Map;

public class InputView {
    public static int printVisitedDateInputMessage() {
        System.out.println(TextType.RESTAURANT_DATE_INPUT_MESSAGE.getText());
        return Parser.convertToInt(Console.readLine());
    }

    public static Map<String, Integer> printMenuInitInputMessage() {
        System.out.println(TextType.MENU_ORDER_INPUT_MESSAGE.getText());
        return Parser.convertToMap(Console.readLine());
    }
}
