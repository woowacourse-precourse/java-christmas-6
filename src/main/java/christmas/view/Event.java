package christmas.view;

import christmas.func.PrintOrderList;

public class Event {

    public void eventStart() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        InputView iv = new InputView();
        int visitDate = iv.readDate();
        String orderMenu = iv.readMenu();
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println(" ");

        PrintOrderList pl = new PrintOrderList();
        pl.printOrderSummary(orderMenu);
    }
}
