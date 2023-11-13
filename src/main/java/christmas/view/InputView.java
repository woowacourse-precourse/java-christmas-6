package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.type.TextType;
import christmas.utils.Parser;

public class InputView {
    public static int printVisitedDateInputMessage() {
        System.out.println(TextType.RESTAURANT_DATE_INPUT_MESSAGE.getText());
        return Parser.convertToInt(Console.readLine());
    }
}
