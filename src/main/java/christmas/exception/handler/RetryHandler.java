package christmas.exception.handler;

import christmas.view.OutputView;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

import christmas.exception.PromotionExceptionMaker;
/**
 * XXXException 을 전달받아 예상한 예외에 대해서만 재시도, 그 외의 예외는 throw
 */
public class RetryHandler{

    public static <T> T getOrRetry(Supplier<T> supplier, String exceptionMessage){

        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                OutputView.printException(exceptionMessage);
            } finally {
                OutputView.newLine();
            }
        }
    }
}
