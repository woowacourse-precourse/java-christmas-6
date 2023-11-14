package christmas.view.input;

import christmas.constant.errorMessage.exception.CustomNullPointAmountException;
import christmas.constant.errorMessage.exception.CustomNumberFormatAmountException;
import christmas.constant.errorMessage.input.ReadExceptionStatus;
import christmas.view.input.reader.Reader;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private static final String PRINT_READ_DATE_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.\n"
            + "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";

    private final Reader reader;

    public InputView(final Reader reader) {
        this.reader = reader;
    }

    private String readLine() {
        return reader.readLine();
    }

    public int readDate() {
        System.out.println(PRINT_READ_DATE_MESSAGE);
        return parseNumber(readLine());
    }

    private String checkReadIsNull(final String input) {
        try {
            return input.trim();
        } catch (NullPointerException e) {
            throw new CustomNullPointAmountException(ReadExceptionStatus.DATE_IS_NOT_CORRECT);
        }
    }

    private int parseNumber(final String input) {
        try {
            return Integer.parseInt(checkReadIsNull(input));
        } catch (NumberFormatException e) {
            throw new CustomNumberFormatAmountException(ReadExceptionStatus.DATE_IS_NOT_CORRECT);
        }
    }
}
