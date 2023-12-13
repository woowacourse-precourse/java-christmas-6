package christmas.view.io;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import christmas.exception.XXXExceptionMaker;

public class Reader {
    public static int getInteger(){
        String input = Console.readLine();
        return parseInt(input);
    }

    private static int parseInt(String input) {
        try{
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw XXXExceptionMaker.INVALID_NUMBER_FORMAT.makeException();
        }
    }

    public static String getString(){
        return Console.readLine();
    }

    public static List<String> getStringsUsingDelimiter(String delimiter) {
        String input = Console.readLine();
        validateNotEndDelimiter(input, delimiter);
        List<String> inputs = Arrays.stream(input.split(delimiter))
                .map(String::trim)
                .collect(Collectors.toList());
        inputs.forEach(Reader::validateNotBlank);
        return inputs;
    }

    private static void validateNotBlank(String input){
        if(input.isBlank()){
            throw XXXExceptionMaker.BLANK_INPUT.makeException();
        }
    }
    private static void validateNotEndDelimiter(String input, String delimiter) {
        if (input.endsWith(delimiter)) {
            throw XXXExceptionMaker.INVALID_INPUT_FORMAT.makeException();
        }
    }

    private static boolean getBoolean(String trueMessage, String falseMessage){
        String input = Console.readLine();
        if(trueMessage.equals(input)){
            return true;
        }
        if(falseMessage.equals(input)){
            return false;
        }
        throw XXXExceptionMaker.INVALID_INPUT_FORMAT.makeException();
    }

    //아래 메서드는 안쓸 것 같긴 한데
    public static <T> List<T> getObjectsUsingDelimiter(Function<String, T> converter, String delimiter){
        List<String> inputStrings = getStringsUsingDelimiter(delimiter);
        return inputStrings.stream()
                .map(converter::apply)
                .collect(Collectors.toList());
    }
}
