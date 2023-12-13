package christmas.exception.handler;

import christmas.view.OutputView;
import java.util.Arrays;
import java.util.function.Supplier;

import christmas.exception.XXXExceptionMaker;
/**
 * XXXException 을 전달받아 예상한 예외에 대해서만 재시도, 그 외의 예외는 throw
 */
public class RetryHandler{

    public static <T> T getOrRetry(Supplier<T> supplier){

        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                OutputView.printException(e);
            } finally {
                OutputView.newLine();
            }
        }
    }
    public static <T> T getOrConditionalRetry(Supplier<T> supplier, XXXExceptionMaker... expectedExceptions){
        while(true){
            try{
                return supplier.get();
            } catch (IllegalArgumentException e){
                checkExpectedException(e, expectedExceptions);
            } finally {
                OutputView.newLine();
            }
        }
    }
    public static void runOrRetry(Runnable runnable) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                OutputView.printException(e);
            } finally {
                OutputView.newLine();
            }
        }
    }
    public static void runOrConditionalRetry(Runnable runnable, XXXExceptionMaker... expectedExceptions) {
        while(true){
            try {
                runnable.run();
                return;
            } catch (IllegalArgumentException e) {
                checkExpectedException(e, expectedExceptions);
            } finally {
                OutputView.newLine();
            }
        }
    }

    private static void checkExpectedException(IllegalArgumentException e, XXXExceptionMaker[] expectedExceptions) {
        if(!isExpectedException(e, expectedExceptions)){
            throw e;
        }
        OutputView.printException(e);
    }

    private static boolean isExpectedException(IllegalArgumentException e, XXXExceptionMaker[] exceptions) {
        return Arrays.stream(exceptions)
                .map(XXXExceptionMaker::makeException)
                .anyMatch(e::equals);
    }
}
