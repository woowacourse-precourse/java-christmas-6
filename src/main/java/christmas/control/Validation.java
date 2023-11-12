package christmas.control;

import christmas.model.FoodType;
import christmas.model.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validation {
    private static Set<String> usedItem = new HashSet<>();
    private static final String DAY_ERROR_MEESAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String FOOD_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";


    public static boolean validDate(String input) {
        final int START_DAY = 1;
        final int END_DAY = 31;
        int date;

        try {
            date = Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println(DAY_ERROR_MEESAGE);
            return false;
        }

        if (date < START_DAY || date > END_DAY) {
            System.out.println(DAY_ERROR_MEESAGE);
            return false;
        }
        return true;
    }

    public static boolean validItems(String input) {
        usedItem.clear();
        int max = 0;
        try {
            String[] splitItems = input.split(",");
            for (String item : splitItems) {
                validItem(Item.extractItem(item), max);
            }
            return true;
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(FOOD_ERROR_MESSAGE);
        }
        return false;
    }

    public static void validItem(Item item, int max) {
        try {
            validFood(item.getFood());
            validCount(item.getCount(), max);
            validDuplication(item.getFood());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static void validFood(String food) {
        try {
            FoodType.valueOf(food).getType();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }

    public static void validCount(int count, int max) {
        final int MIN_COUNT = 1;
        final int MAX_COUNT = 20;
        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new IllegalArgumentException();
        }
        if (max > MAX_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    public static void validDuplication(String food) {
        if (usedItem.contains(food)) {
            throw new IllegalArgumentException();
        }
        usedItem.add(food);
    }
}
