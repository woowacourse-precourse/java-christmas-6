package christmas.view;

import christmas.domain.Orders;
import christmas.domain.VisitDate;
import christmas.view.io.Printer;
import java.util.List;

public class OutputView {
    public static final String EXCEPTION_PREFIX = "[ERROR] ";

    public static void printException(Exception e){
        Printer.printMessage(EXCEPTION_PREFIX + e.getMessage());
    }

    public static void newLine(){
        Printer.printMessage("");
    }

    public static void sayHello(){
        Printer.printMessage("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public static void printResultHead(VisitDate visitDate){
        Printer.printMessageUsingFormat("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDate.getDayOfMonth());
    }

    public static void printOrders(Orders orders){
        orders.getOrders().forEach(
                menuAndCount ->
                        Printer.printMessageUsingFormat("%s %d개", menuAndCount.getMenuName(), menuAndCount.getCount())
        );
        newLine();
    }




    private static <T> void printListUsingFormat(List<T> list){
        list.forEach(t -> Printer.printMessageUsingFormat("FORMAT", 1, 2, 3));
    }
}
