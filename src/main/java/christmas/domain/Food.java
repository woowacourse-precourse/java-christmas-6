package christmas.domain;

import christmas.type.MenuType;

import java.util.Arrays;

public class Food {
    private String foodName;
    private int count;

    public Food(String foodName, int count) {
        this.foodName = foodName;
        this.count = count;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCount() {
        return count;
    }
}
