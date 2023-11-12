package christmas.views;

import christmas.exceptions.RestaurantException;

public class OutputView {
    public static void printOut(String message) {
        System.out.println(message);
    }

    public static void printException(RestaurantException e) {
        System.out.println(e.getMessage());
    }

}