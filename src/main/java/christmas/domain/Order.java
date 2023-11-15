package christmas.domain;

import christmas.type.FoodType;
import christmas.type.MenuType;
import christmas.utils.Parser;
import christmas.utils.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static christmas.type.MenuType.getPriceByName;

public class Order {
    private List<Food> appetizerFoods = new ArrayList<>();
    private List<Food> mainFoods = new ArrayList<>();
    private List<Food> dessertFoods = new ArrayList<>();
    private List<Food> drinkFoods = new ArrayList<>();

    private int totalPrice = 0;

    public void addOrder(String foodName, int count) {
        validate(foodName);
        FoodType foodType = MenuType.findFoodType(foodName);
        distributeFood(foodName, count, foodType);
        addTotalPrice(foodName, count);
    }

    private void addTotalPrice(String foodName, int count) {
        totalPrice += getPriceByName(foodName) * count;
    }

    public HashMap<String, Integer> getTotalOrder() {
        HashMap<String, Integer> orderMap = new HashMap<>();
        Parser.convertListToMap(orderMap, appetizerFoods);
        Parser.convertListToMap(orderMap, mainFoods);
        Parser.convertListToMap(orderMap, dessertFoods);
        Parser.convertListToMap(orderMap, drinkFoods);
        return orderMap;
    }

    private void distributeFood(String foodName, int count, FoodType foodType) {
        if (foodType.equals(FoodType.APPETIZER)) {
            appetizerFoods.add(new Food(foodName, count));
        }

        if (foodType.equals(FoodType.MAIN)) {
            mainFoods.add(new Food(foodName, count));
        }

        if (foodType.equals(FoodType.DESSERT)) {
            dessertFoods.add(new Food(foodName, count));
        }

        if (foodType.equals(FoodType.DRINK)) {
            drinkFoods.add(new Food(foodName, count));
        }
    }


    private void validate(String foodName) {
        Validator.checkFoodValid(foodName);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<Food> getMainFoods() {
        return mainFoods;
    }

    public List<Food> getDessertFoods() {
        return dessertFoods;
    }
}
