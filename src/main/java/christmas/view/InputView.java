package christmas.view;

import christmas.global.exception.CustomException;
import christmas.global.exception.ErrorMessage;
import christmas.view.console.ConsoleReader;
import christmas.view.console.ConsoleWriter;

public class InputView {
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;

    public int readDay() {
        ConsoleWriter.printlnMessage("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Validator.validateDay(ConsoleReader.enterMessage());
    }

    private static class Validator {
        public static int validateDay(String message) {
            int number = validateNumber(message);
            validateRange(number, START_DAY, END_DAY);
            return number;
        }

        public static int validateNumber(String message) {
            if (isNotNumber(message)) {
                throw CustomException.from(ErrorMessage.INVALID_DAY_ERROR);
            }
            return Integer.parseInt(message);
        }

        private static boolean isNotNumber(String str) {
            return !str.matches("\\d+");
        }

        public static void validateRange(int number, int start, int end) {
            if (isInvalidRange(number, start, end)) {
                throw CustomException.from(ErrorMessage.INVALID_DAY_ERROR);
            }
        }

        private static boolean isInvalidRange(int number, int start, int end) {
            return number < start || number > end;
        }
    }
}
