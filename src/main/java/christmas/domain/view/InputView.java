package christmas.domain.view;

import christmas.domain.util.Printer;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class InputView {
    Printer printer;

    public InputView(Printer printer) {
        this.printer = printer;
    }

    public int readDate(){
        printer.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        try{
            return Integer.parseInt(readLine());
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public String readMenu(){
        printer.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        return readLine();
    }


}
