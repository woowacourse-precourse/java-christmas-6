package christmas.view;

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



    private static <T> void printListUsingFormat(List<T> list){
        list.forEach(t -> Printer.printMessageUsingFormat("FORMAT", 1, 2, 3));
    }
}
