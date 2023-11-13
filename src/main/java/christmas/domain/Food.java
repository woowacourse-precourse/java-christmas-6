package christmas.domain;

import christmas.type.MenuType;

import java.util.Arrays;

public class Food {
    private String foodName;
    private int count;

    public Food(String foodName, int count) {
        validate(foodName);
        this.foodName = foodName;
        this.count = count;
    }

    private void validate(String foodName) {
        boolean isExist = Arrays.stream(MenuType.values())
                .anyMatch(v -> v.getFoodName().equals(foodName));
        if (isExist) {
            throw new IllegalArgumentException();
        }
    }
}
