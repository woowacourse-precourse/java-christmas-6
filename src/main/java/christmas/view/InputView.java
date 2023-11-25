package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.OrderList;
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

    public OrderList readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        OrderList orderList;
        while(true){
            try{
                String[] inputOrders = Console.readLine().split(",");
                orderList = new OrderList(inputOrders);
                break;
            } catch(IllegalArgumentException e){
                inputException.orderInputError();
            }
        }
        return orderList;
    }
}
