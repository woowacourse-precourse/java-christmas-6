package christmas.exceptions;

public class RestaurantException extends IllegalArgumentException{
    public RestaurantException(String message) {
        super("[ERROR] " +message);
    }
}
