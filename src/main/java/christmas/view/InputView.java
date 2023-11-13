package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import static java.lang.System.out;

public class InputView {
    private static String errorPrefix = "[ERROR]";
    private int date;


    public void receiveReservationDate() {
        do {
            out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        } while(!promptForReservationDate());
    }

    private boolean promptForReservationDate() {
        String dateInput = Console.readLine();

        try {
          validateDateInput(dateInput);
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
            return false;
        }

        date = Integer.parseInt(dateInput);
        return true;
    }


    private void validateDateInput (String input) throws IllegalArgumentException {
        if (!input.matches("^-?\\d+$")) {
            throw new IllegalArgumentException(errorPrefix + "정수 입력");
        }

        int dateToCheck = Integer.parseInt(input);

        if (dateToCheck < 1 || dateToCheck > 31) {
            throw new IllegalArgumentException(errorPrefix + "1부터 31 이내의 날짜 입력");
        }
    } // TODO : 날짜는 누가 가지고 있는 것이 좋을까

}
