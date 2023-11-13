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


}
