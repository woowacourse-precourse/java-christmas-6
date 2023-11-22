package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.exception.InputException;

public class InputView {
    InputException inputException = new InputException();

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int date = 0;
        while(true){
            try{
                date = Integer.parseInt(Console.readLine());
                validateDate(date);
                break;
            } catch(IllegalArgumentException e){
                inputException.dateInputError();
            }
        }
        return date;
    }

    private void validateDate(int date) throws IllegalArgumentException{
        if(date < 1 || date > 31){
            throw new IllegalArgumentException();
        }
    }
}
