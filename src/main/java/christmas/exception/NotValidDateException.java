package christmas.exception;

public class NotValidDateException extends IllegalArgumentException{
    public NotValidDateException(String text) {
        super(text);
    }
}
