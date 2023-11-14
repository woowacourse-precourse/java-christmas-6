package christmas.domain.view;

import christmas.domain.entity.Order;
import christmas.domain.util.Printer;

public class OutputView {
    Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

    public void printPreface(){
        printer.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printMenu(Order order) {
        printer.println("<주문 메뉴>");
        order.getOrderMap().forEach((key, value) -> printer.println(key + " " + value + "개"));
    }
}
