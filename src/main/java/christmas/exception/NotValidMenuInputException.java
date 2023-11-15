package christmas.exception;

public class NotValidMenuInputException extends IllegalArgumentException{

    public NotValidMenuInputException(String text) {
        super(text);
    }
}
