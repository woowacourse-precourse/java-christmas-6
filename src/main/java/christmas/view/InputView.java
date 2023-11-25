package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.ReservationDate;
import christmas.exception.InputException;

public class InputView {
    InputException inputException = new InputException();

    public ReservationDate readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        ReservationDate date;
        while(true){
            try{
                int input = Integer.parseInt(Console.readLine());
                date = new ReservationDate(input);
                break;
            } catch(IllegalArgumentException e){
                inputException.dateInputError();
            }
        }
        return date;
    }
}
