package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final int WRONG_VALUE = -1;
    private static InputView inputView;

    private InputView() {

    }

    public static InputView getInstance() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return 0;
    }
    // ...
}
